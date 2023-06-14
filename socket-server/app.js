import { createServer } from "http";
import { Server } from "socket.io";

const httpServer = createServer();
const io = new Server(httpServer, {
	cors: {
		origin: "http://localhost:4200"
	}
});

httpServer.listen(2002, () => {
	console.log('listening on *:2002');
});



io.sockets.on('connection', function (socket) {
	socket.userData = { x: 0, y: 0, z: 0, heading: 0 };//Default values;

	console.log(`${socket.id} connected`);
	//socket.emit('rooms', getRooms('connected'));
	socket.emit('setId', { id: socket.id });

	socket.on('join room', function (room) {
		console.log(`A new user joined room ${room}`);
		socket.roomName = room;
		socket.join(room);
		//io.emit('rooms', getRooms('joined room'));
	});

	socket.on('get room', function () {
		socket.emit('rooms', getRooms());
	});

	socket.on('set username', function (name) {
		console.log(`username set to ${name}(${socket.id})`);
		socket.username = name;
	});

	socket.on('disconnect', function () {
		socket.broadcast.emit('deletePlayer', { id: socket.id });
	});

	socket.on('init', function (data) {
		console.log(`socket.init ${data.model}`);
		socket.userData.model = data.model;
		socket.userData.colour = data.colour;
		socket.userData.x = data.x;
		socket.userData.y = data.y;
		socket.userData.z = data.z;
		socket.userData.heading = data.h;
		socket.userData.pb = data.pb,
			socket.userData.action = "Idle";
	});

	socket.on('update', function (data) {
		socket.userData.x = data.x;
		socket.userData.y = data.y;
		socket.userData.z = data.z;
		socket.userData.heading = data.h;
		socket.userData.pb = data.pb,
			socket.userData.action = data.action;
	});

	socket.on('chat message', function (data) {
		console.log(`chat message:${data.id} ${data.message}`);
		io.to(data.id).emit('chat message', { id: socket.id, message: data.message });
	})
});




setInterval(function () {
	const nsp = io.of('/');
	const rooms = nsp.adapter.rooms;

	for (const [key, value] of rooms) {
		if(key.length>6)continue;
		
		let pack = [];
		for (const socketId of value) {
			
		
			const socket = io.sockets.sockets.get(socketId);
			if (socket === undefined || socket.username === undefined || socket.roomName === undefined) continue;
			
			if (socket.userData.model !== undefined) {
				pack.push({
					id: socket.id,
					model: socket.userData.model,
					colour: socket.userData.colour,
					x: socket.userData.x,
					y: socket.userData.y,
					z: socket.userData.z,
					heading: socket.userData.heading,
					pb: socket.userData.pb,
					action: socket.userData.action
				});
			}
		}

		if (pack.length > 0) io.to(key).emit('remoteData', pack);

	}


}, 40);

//这个函数得到roomName+里面的username的list
//这个函数的目的仅仅只是把adapter中的rooms翻译一下
function getRooms(msg) {

	const nsp = io.of('/');
	const rooms = nsp.adapter.rooms;
	let pack = {};
	if(rooms.has('画展')){
		pack.huazhan = rooms.get('画展').size;
	}else
		pack.huazhan = 0;
	if(rooms.has('其他')){
		pack.qita = rooms.get('其他').size;
	}else
		pack.qita = 0;
	
	return pack;
}