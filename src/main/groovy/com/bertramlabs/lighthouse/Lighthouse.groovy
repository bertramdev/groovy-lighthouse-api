package com.bertramlabs.lighthouse

class Lighthouse {
	def url
	def apiToken

	def project = {id ->
		def req = new LHRequestBuilder(url: "${url}/projects/${id}.json", apiToken: apiToken)
		new LHProject(_request: req)
	}

	def projects = {->
		def req = new LHRequestBuilder(url: "${url}/projects.json", apiToken: apiToken)
		req.get().projects.collect {proj ->
			new LHProject(
			_json: proj.project,
			_request: req.newSubRequest("/projects/${proj.id}.json"))
		}
	}

	private buildRequest() {
		checkArgs()
	}
}