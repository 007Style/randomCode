REM Useful Key Generation Commands
REM Create the public/private key pair.
keytool -genkey -alias fiddle -keyalg RSA -validity 7 -keystore keystore 

REM Make sure it worked.
keytool -list -v -keystore keystore

REM Export the self signed certificate
keytool -export -alias fiddle -keystore keystore -rfc -file fiddle.cer

REM Import the certificate into a trusted entity store
keytool -import -alias fiddlecert -file fiddle.cer -keystore truststore

REM Make sure it worked.
keytool -list -v -keystore truststore 

REM Use this tag on command line to indicate path to truststore.
REM java -Djavax.net.ssl.keyStore=keystore -Djavax.net.ssl.keyStorePassword=password Server
REM java -Djavax.net.ssl.trustStore=truststore -Djavax.net.ssl.trustStorePassword=trustword Client