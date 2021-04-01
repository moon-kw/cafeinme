package com.kh.cafeinme.search.svc;

import org.springframework.stereotype.Component;

@Component
public class KeywordsetSVC {
	public String keyset(String keyword) {
		String x = keyword.replace(" ", "");
		String[] y = x.split("");
		String z = "%";
		for (int i=0; i<y.length; i++) {
			z += y[i]+"%";
		}
		return z;
	}
}
