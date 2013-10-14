package com.bertramlabs.lighthouse

import groovy.json.JsonSlurper

class LHRequestBuilder {
	def url
	def apiToken

	def newSubRequest(path) {
		def splits = url.split('/')
		def newRoot = splits[0..(splits.length - 2)].join('/')
		new LHRequestBuilder(apiToken: apiToken, url: "${newRoot}${path}")
	}

	def get() {
println "URL :: $url"
		def reqUrl = new URL(url)
		def conn = reqUrl.openConnection()
		conn.setRequestProperty('Content-Type', 'application/json')
		conn.setRequestProperty('X-LighthouseToken', apiToken)
		if(conn.responseCode == 200) {
			new JsonSlurper().parseText(conn.content.text)
		} else {
			throw new IOException()
		}
	}

	private checkArgs() {
		if(!url) {
			throw new IllegalStateException('URL must be set')
		}
		if(!apiToken) {
			throw new IllegalStateException('API token must be specified')
		}
	}
}