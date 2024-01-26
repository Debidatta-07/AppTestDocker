package com.cavisson.ata.utils;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class googleUrl extends GenericUrl {

	public googleUrl(String encodedUrl) {
		super(encodedUrl);
	}

	@Key
	public int per_page;

	@Key
	public int page;

}