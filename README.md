### Initial Steps

1. start server
2. Initialization Data : execute get Request `http://localhost:8443/textanalyser/api/auth/loadInitialData` to 	initialize user data into cache
3. Authentications : execute post Request `http://localhost:8443/textanalyser/api/auth/token` here pass parameters<br> 

		Admin:
		userName: admin
		password: admin
	
		user:
		userName: deep
		password: 12345
	
get AccessToken and use that token for the further uses
4.  Text analyze :  execute post request `http://localhost:8443/textanalyser/analyze`

	header --> Authorization: 'Bearer AccessToken(received in Authentication call)'
	parameter --> text : 'text value ....'
5. longest-word : execute get request `http://localhost:8443/textanalyser/analyze` to get longest word.
6. I have enableSwagget too for the same `http://localhost:8443/textanalyser/v2/api-docs`

