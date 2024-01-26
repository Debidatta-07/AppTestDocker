package com.cavisson.ata.services;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;

public class LDAPService {

	public static String add(String employee_number, String firstname, String lastname, String host, String port,
			String organisation, String organisation_unit) throws NamingException {

		Hashtable<String, Object> env = new Hashtable<>();

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);

		DirContext context = new InitialDirContext(env);
		System.out.println("Directory Connected..");
		// System.out.println(employee_number);

		BasicAttributes attributes = new BasicAttributes();
		Attribute attribute = new BasicAttribute("objectClass");

		attribute.add("inetOrgPerson");
		attributes.put(attribute);

		Attribute sn = new BasicAttribute("sn");
		sn.add(lastname);
		Attribute cn = new BasicAttribute("cn");
		cn.add(firstname);

		attributes.put(sn);
		attributes.put(cn);

		String empadd = "employeeNumber=" + employee_number + ",ou=" + organisation_unit + ",o=" + organisation;

		System.out.println(empadd);
		context.createSubcontext(empadd, attributes);
		// context.createSubcontext(emp, attributes);

		System.out.println("context  created successfully");

		return "context created successfully";
	}

	public static String delete(String employee_number, String firstname, String surname, String host, String port,
			String organisation, String organisation_unit) throws NamingException {

		Hashtable<String, Object> env = new Hashtable<>();

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);

		DirContext context = new InitialDirContext(env);
		System.out.println("Directory Connected..");
		// System.out.println("del" + employee_number);
		String empdel = "employeeNumber=" + employee_number + ",ou=" + organisation_unit + ",o=" + organisation;
		System.out.println(empdel);
		context.destroySubcontext(empdel);
		// context.destroySubcontext("employeeNumber=11,ou=users,o=Company");

		System.out.println("context  deleted successfully");

		return "context deleted successfully";
	}

	@SuppressWarnings("unused")
	public static String fetch(String employee_number, String firstname, String surname, String host, String port,
			String organisation, String organisation_unit) throws NamingException {

		Properties initialProperties = new Properties();

		initialProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		initialProperties.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);
		initialProperties.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		initialProperties.put(Context.SECURITY_CREDENTIALS, "secret");
		DirContext context = new InitialDirContext(initialProperties);
		System.out.println("Directory Connected..");
		System.out.println(employee_number);
		String searchFilter = "(objectClass=inetOrgPerson)";
		String[] requiredAttributes = { "sn", "cn", "employeeNumber" };
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(requiredAttributes);

		NamingEnumeration<SearchResult> users = context.search("ou=" + organisation_unit + ",o=" + organisation,
				searchFilter, controls);
		String resultFinal = null;
		SearchResult searchResult = null;
		String commonName = null;
		String lastname = null;
		String employeeNum = null;
		String result = null;
		while (users.hasMore()) {

			searchResult = (SearchResult) users.next();
			Attributes attr = searchResult.getAttributes();

			commonName = attr.get("cn").get(0).toString();
			lastname = attr.get("sn").get(0).toString();

			employeeNum = attr.get("employeeNumber").get(0).toString();

			if (attr.get("employeeNumber") == null)
				System.out.println("no employee");

			System.out.println("Firstname = " + commonName);
			System.out.println("Lastname  = " + lastname);
			System.out.println("Employee Number = " + employeeNum);
			System.out.println("-------------------------------------------");

			result = result + "," + "     Name = " + commonName + "     Lastname = " + lastname
					+ "     Employee Number = " + employeeNum + "\t";

		}

		if (result == null) {
			result = "no data";
			return result;
		}
		return result.replace("null,", "");
	}

	public static String modify(String employee_number, String firstname, String lastname, String host, String port,
			String organisation, String organisation_unit) throws NamingException {

		Properties properties = new Properties();

		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);
		properties.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		properties.put(Context.SECURITY_CREDENTIALS, "secret");

		DirContext context = new InitialDirContext(properties);
		System.out.println("Directory Connected..");
		Attributes attr = context.getAttributes(
				"employeeNumber=" + employee_number + ",ou=" + organisation_unit + ",o=" + organisation + "");
		// System.out.println("Before update.." + attr.toString());
		System.out.println("Updating..");
		Attribute attribute = new BasicAttribute("sn", lastname);
		Attribute attribute1 = new BasicAttribute("cn", firstname);

		ModificationItem[] item = new ModificationItem[2];

		item[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute);
		item[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attribute1);
		context.modifyAttributes(
				"employeeNumber=" + employee_number + ",ou=" + organisation_unit + ",o=" + organisation + "", item);
		// System.out.println("After update .."+attr.toString());

		System.out.println("context modified successfully");

		return "context modified successfully";
	}

	public static String moddn(String employee_number, String firstname, String host, String port, String organisation,
			String organisation_unit, String newpath) throws Exception {

		Properties properties = new Properties();

		properties.put(Context.SECURITY_AUTHENTICATION, "simple");
		properties.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		properties.put(Context.SECURITY_CREDENTIALS, "secret");
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);
		properties.put("java.naming.ldap.deleteRDN", "false");

		DirContext context = new InitialDirContext(properties);
		System.out.println("Directory Connected..");
		context.rename("employeeNumber=" + employee_number + ",ou=" + organisation_unit + ",o=" + organisation + "",
				"employeeNumber=" + employee_number + ",ou=" + newpath + ",o=" + organisation + "");
		System.out.println("Entry Moved..");

		return "Context Moved Successfully";

	}

	public static String compare(String employee_number, String firstname, String host, String port,
			String organisation, String organisation_unit) throws NamingException {

		Hashtable<String, Object> env = new Hashtable<String, Object>(11);

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);

		// Create initial context
		DirContext ctx = new InitialDirContext(env);

		// Value of attribute
		byte[] key = { (byte) 0x61, (byte) 0x62, (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67 };

		// Set up search controls
		SearchControls ctls = new SearchControls();
		ctls.setReturningAttributes(new String[0]); // return no attrs
		ctls.setSearchScope(SearchControls.OBJECT_SCOPE); // search object only

		// Invoke search method that will use the LDAP "compare" operation
		NamingEnumeration<SearchResult> answer = ctx.search(
				"employeeNumber=" + employee_number + ",ou=" + organisation_unit + ",o=" + organisation + "",
				"(employeeNumber={0})", new Object[] { key }, ctls);

		// Print the answer
		// printSearchEnumeration(answer);

		System.out.println(answer);

		// Close the context when we're done
		// ctx.close();

		return "The named entry has an attribute/value pair :" + answer.toString();

	}

	public static String extendedStartTls(String host, String port) throws NamingException, IOException {

		Properties properties = new Properties();

		properties.put(Context.SECURITY_AUTHENTICATION, "simple");
		properties.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		properties.put(Context.SECURITY_CREDENTIALS, "secret");
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(Context.PROVIDER_URL, "ldap://" + host + ":" + port);
		// Open an LDAP association

		LdapContext ctx = new InitialLdapContext(properties, null);

		// Perform a StartTLS extended operation
		StartTlsResponse tls = (StartTlsResponse) ctx.extendedOperation(new StartTlsRequest());

		System.out.println(tls);

		return "TLS connected :" + tls;

	}

}
