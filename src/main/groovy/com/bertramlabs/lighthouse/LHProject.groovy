package com.bertramlabs.lighthouse

class LHProject extends LHObject {

	def ticket = {id ->
		def req = _request.newSubRequest("/tickets/${id}.json")
		new LHTicket(_request: req)
	}

	def tickets = {->
		def req = _request.newSubRequest("/${id}/tickets.json")
		req.get().tickets.collect {ticket ->
			new LHTicket(
			_json: ticket.ticket,
			_request: req.newSubRequest("/${ticket.id}.json"))
		}
	}
}