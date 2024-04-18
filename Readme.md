# SOAP AND JSON CONVERSION

# prerequisites

- xml
    - header [Content-type = text/xml]
- json 
    - header [Content-type = application/json]
- my soap server
    - [my soap server link](https://github.com/Xlt3h/soap.java.git)
- java
    - version 17 or 21
    - springboot
    - Tested on ubuntu 20.4
    - Springboot 3.2.4
## soap input 

```xml
 <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://superdev.io/student-service">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:getStudentRequest>
         <ser:id>55</ser:id>
      </ser:getStudentRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
## soap response in json
```json
{
    "ns2:studentNumber": "S55",
    "ns2:id": 55,
    "ns2:grade": 2
}
```
## json input
```json
{
    "id": 1
}
```
## json response in xml
```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <ns2:getStudentResponse xmlns:ns2="http://superdev.io/student-service">
            <ns2:student>
                <ns2:grade>1</ns2:grade>
                <ns2:id>1</ns2:id>
                <ns2:studentNumber>S1</ns2:studentNumber>
            </ns2:student>
        </ns2:getStudentResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
