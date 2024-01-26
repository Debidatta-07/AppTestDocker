function validate() {
	var name = document.getElementById("name").value;
	if (name == '') {
		alert('Please enter a valid name.');
		return false;
	} else {
		return true;
	}
}

function cloudant() {
	document.getElementById('cloudant').style.display = 'block';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function db2() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'block';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function mssql() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'block';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function mysql() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'block';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function oracle() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'block';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function postgres() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'block';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function redis() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function lettuce() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'block';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}
function redissyncclient() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'block';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}

function redisasyncclient() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'block';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'none';
}

function cassandraclient() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'block';
	document.getElementById('mongoclient').style.display = 'none';
}
function mongoclient() {
	document.getElementById('cloudant').style.display = 'none';
	document.getElementById('db2').style.display = 'none';
	document.getElementById('mssql').style.display = 'none';
	document.getElementById('mysql').style.display = 'none';
	document.getElementById('oracle').style.display = 'none';
	document.getElementById('postgres').style.display = 'none';
	document.getElementById('lettuce').style.display = 'none';
	document.getElementById('redissyncclient').style.display = 'none';
	document.getElementById('redisasyncclient').style.display = 'none';
	document.getElementById('cassandraclient').style.display = 'none';
	document.getElementById('mongoclient').style.display = 'block';

}
function add() {
	document.getElementById('add').style.display = 'block';
	document.getElementById('fetch').style.display = 'none';
	document.getElementById('delete').style.display = 'none';
	document.getElementById('modify').style.display = 'none';
	document.getElementById('moddn').style.display = 'none';
	document.getElementById('compare').style.display = 'none';
	document.getElementById('extendedStartTls').style.display = 'none';
}
function fetch() {
	document.getElementById('add').style.display = 'none';
	document.getElementById('fetch').style.display = 'block';
	document.getElementById('delete').style.display = 'none';
	document.getElementById('modify').style.display = 'none';
	document.getElementById('moddn').style.display = 'none';
	document.getElementById('compare').style.display = 'none';
	document.getElementById('extendedStartTls').style.display = 'none';
}
function deleteById() {
	document.getElementById('add').style.display = 'none';
	document.getElementById('fetch').style.display = 'none';
	document.getElementById('delete').style.display = 'block';
	document.getElementById('modify').style.display = 'none';
	document.getElementById('moddn').style.display = 'none';
	document.getElementById('compare').style.display = 'none';
	document.getElementById('extendedStartTls').style.display = 'none';
}
function modify() {
	document.getElementById('add').style.display = 'none';
	document.getElementById('fetch').style.display = 'none';
	document.getElementById('delete').style.display = 'none';
	document.getElementById('modify').style.display = 'block';
	document.getElementById('moddn').style.display = 'none';
	document.getElementById('compare').style.display = 'none';
	document.getElementById('extendedStartTls').style.display = 'none';
}
function moddn() {
	document.getElementById('add').style.display = 'none';
	document.getElementById('fetch').style.display = 'none';
	document.getElementById('delete').style.display = 'none';
	document.getElementById('modify').style.display = 'none';
	document.getElementById('moddn').style.display = 'block';
	document.getElementById('compare').style.display = 'none';
	document.getElementById('extendedStartTls').style.display = 'none';
}
function compare() {
	document.getElementById('add').style.display = 'none';
	document.getElementById('fetch').style.display = 'none';
	document.getElementById('delete').style.display = 'none';
	document.getElementById('modify').style.display = 'none';
	document.getElementById('moddn').style.display = 'none';
	document.getElementById('compare').style.display = 'block';
	document.getElementById('extendedStartTls').style.display = 'none';
}
function extendedStartTls() {
	document.getElementById('add').style.display = 'none';
	document.getElementById('fetch').style.display = 'none';
	document.getElementById('delete').style.display = 'none';
	document.getElementById('modify').style.display = 'none';
	document.getElementById('moddn').style.display = 'none';
	document.getElementById('compare').style.display = 'none';
	document.getElementById('extendedStartTls').style.display = 'block';

}

function sendAMQ() {
	document.getElementById('sendAMQ').style.display = 'block';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}
function receiveAMQ() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'block';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}

function topicpublisher() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'block';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}

function topicsubscriber() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'block';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}

function rabbitmqsender() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'block';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}

function rabbitmqreceiver() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'block';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}

function ibmmqsender() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'block';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}

function ibmmqreceiver() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'block';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'none';
}
function kafkaproducer() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'block';
	document.getElementById('kafkaconsumer').style.display = 'none';
}

function kafkaconsumer() {
	document.getElementById('sendAMQ').style.display = 'none';
	document.getElementById('receiveAMQ').style.display = 'none';
	document.getElementById('topicpublisher').style.display = 'none';
	document.getElementById('topicsubscriber').style.display = 'none';
	document.getElementById('rabbitmqsender').style.display = 'none';
	document.getElementById('rabbitmqreceiver').style.display = 'none';
	document.getElementById('ibmmqsender').style.display = 'none';
	document.getElementById('ibmmqreceiver').style.display = 'none';
	document.getElementById('kafkaproducer').style.display = 'none';
	document.getElementById('kafkaconsumer').style.display = 'block';
}
