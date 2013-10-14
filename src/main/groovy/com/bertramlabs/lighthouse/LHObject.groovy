package com.bertramlabs.lighthouse

class LHObject {
	def _request
	def _json

	def propertyMissing(String name) {
		loadJSON()
		def apiName = name.replaceAll(/\B[A-Z]/) { '_' + it }.toLowerCase() 

		_json[apiName]
	}

	private loadJSON() {
		if(_json) {
			return
		}

		_json = _request.get()
		if(_json.size() == 1) {
			_json = _json.values().first()
		}
	}
	
}