<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>

<%-- <link rel="stylesheet" href="<spring:url value="/css/connect4.css"/>"> --%>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.circle {
	border-radius: 50%;
	behavior: url(PIE.htc);
}

.yellow {
	width: 80px;
	height: 80px;
	background: yellow;
	border: 3px solid black;
}

.red {
	width: 80px;
	height: 80px;
	background: red;
	border: 3px solid black;
}

#board {
	text-align: center;
}

.board {
	display: inline-block;
	border: .2em solid rgba(0, 0, 0, 0.15);
	border-radius: 5px;
	margin: 50px 0 0;
	cursor: pointer;
	position: relative;
}

.board>aside {
	float: left;
	border-left: .12em dashed #AAA;
	position: relative;
}

.board>aside:first-child {
	border: none;
}

.board>aside>div {
	width: 50px;
	height: 50px;
	line-height: 50px;
	/* 	border-top: .12em dashed #D1D1D1; */
}

.board>aside>div:first-child {
	border: none;
}

h1 {
	color: #979789;
	padding: 10px 0 0;
	font-size: 3em;
	text-align: center;
}

.board b {
	display: inline-block;
	vertical-align: middle;
	line-height: normal;
	visibility: hidden;
	border-radius: 40px;
	border-radius: 40px;
	box-shadow: 1px 13px 7px rgba(255, 255, 255, 0.6) inset;
}

.board b b {
	margin: 3px;
	padding: 22px;
	box-shadow: 0 12px 30px rgba(255, 255, 255, 0.4) inset, 0 -15px 0
		rgba(255, 255, 255, 0.05) inset;
}

.board .red b {
	visibility: visible;
	background-color: #CF1919;
}

.board .blue b {
	visibility: visible;
	background-color: #1744BF;
}
</style>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
var playerId,color;
$( document ).ready(function() {
	playerId = $('#playerId').val();
	color = $('#color').val();
	$('#newGameButton').on( "click", function () {
		newGame();
	}); 
	
    newGame ();
    setInterval(getStatus, 1000);
});


function initBoard(totalRows, totalCols) {

	var col, board = $('<div>').addClass('board');
	for (var i=0;i< totalCols;i++) {
		col = $('<aside id="'+i+'">').appendTo(board);
		for (j=0;j<totalRows ; j++) {
			var square = $('<div id="'+j+'">');
			square.on( "click", function () {
				var row = $(this).attr("id");
				var col = $(this).parent().attr("id");
				makeMove(row,col);
			}); 
			square.appendTo(col);
		}
		$('#board').append(board);
	}
}

	function getStatus() {

		$.getJSON("/gameStatus", function(data) {
			var col = data.lastMoveCol;
			var row = data.lastMoveRow;
			var lastPlayer = data.lastMovePlayer;
			var lastMoveColor = data.lastMoveColor;
			var status = data.status;
			
			if (lastPlayer != playerId) {
				var colOnScreen = $('aside')[col];
				$(colOnScreen).children('#'+row).addClass( "circle "+lastMoveColor);	
			}
		
			switch (status) {
					case "PLAYER2_WON":
						$('#statusMessage').html("Player 2 has won the game");
						break;
					case "PLAYER1_WON":
						$('#statusMessage').html("Player 1 has won the game");
						break;
					case "DRAW":
						$('#statusMessage').html("The game is draw");
						break;
		
					}

		});
	}
	function makeMove(row, column) {
		$.getJSON("/makeMove?row=" + row + "&col=" + column
				+ "&player=" + playerId, function(data) {
			if (data.validMove == true) {
				var colOnScreen = $('aside')[column];
				$(colOnScreen).children('#' + row).addClass("circle " + color);
				console.log("Data: " + data.validMove);
			}
		});
	}
	function newGame () {
		$.get("/newGame?player="+playerId, function(data){ 
	    	var jsonResponse = $.parseJSON(data);
	    	var rows = jsonResponse.rows;
	    	var cols = jsonResponse.columns;
	    	var started = jsonResponse.started;
	    	initBoard (rows,cols);
	    });
	}
</script>
</head>
<body>
	<input type="hidden" id="playerId" value="${playerId}" />
	<input type="hidden" id="color" value="${color}" />
	<h1>Connect 4</h1>

	<h3>Player : ${playerId}</h3>
	<span id="statusMessage"><span> <input type="button" value="newGame" id="newGameButton" />
	<div id='board'></div>
</body>
</html>