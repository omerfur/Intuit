# Players project

### Project Structure
`/src/*` structure follows default Java structure.
`/src/main/java/com.intuit/configuration` - Configure all the data during the system load.
The source of Players.csv configured in `/src/main/java/resources/application.properties:file.path`

### Application Programming Interfaces:
[/api/player/{playerId}](http://localhost:8080/api/player/{playerId}) - get single player by id.
Return 200 if found and 404 if not found.

[/api/players](http://localhost:8080/api/players}) - get all players.
Return list of all players. For now without pagination.
