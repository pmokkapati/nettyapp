* Application using Netty and MacWire
* You can start the server locally and call the server
* examples  
* http://localhost:8080/units/si?units=(degree/min*hour/day*degree)
* http://localhost:8080/units/si?units=(degree/min*hour)
* http://localhost:8080/units/si?units=(degree/min*hour*t)
* http://localhost:8080/units/si?units=(degree/min*hour*t/day)
* http://localhost:8080/units/si?units=(degree/min*hour%2Bday*degree%2Dh)
* If you want to use Docker take comments out from build.sbt 
* run sbt Docker/publishLocal
