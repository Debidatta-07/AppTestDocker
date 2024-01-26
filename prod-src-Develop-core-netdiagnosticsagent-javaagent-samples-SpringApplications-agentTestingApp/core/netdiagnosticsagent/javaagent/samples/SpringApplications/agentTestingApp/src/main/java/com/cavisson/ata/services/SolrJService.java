package com.cavisson.ata.services;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrJService {

	public static String searcher(String host, String port, String collection) throws IOException, SolrServerException {
		SolrClient client = new HttpSolrClient.Builder("http://" + host + ":" + port + "/solr/" + collection).build();

		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.addFilterQuery("cat:book");
		query.setFields("cat", "id", "name");
		query.setStart(0);
		query.set("defType", "edismax");

		QueryResponse response = client.query(query);
		SolrDocumentList results = response.getResults();
		for (int i = 0; i < results.size(); ++i) {
			System.out.println(results.get(i));
		}
		return "search completed !---------to know the search result please check application console";
	}

	public static String populate(String host, String port, String collection) throws IOException, SolrServerException {
		SolrClient client = new HttpSolrClient.Builder("http://" + host + ":" + port + "/solr/" + collection).build();
		for (int i = 0; i < 100; ++i) {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("cat", "book");
			doc.addField("id", "book-" + i);
			doc.addField("name", "The Legend of the Hobbit part " + i);
			client.add(doc);
			if (i % 100 == 0)
				client.commit();
			// periodically flush
		}
		client.commit();
		return "populate completed !";
	}
}
