# Create 2 REST services: one that allows to register a user and the other one that displays the details
of a registered user.

Requirements:

- define a user (what are the fields needed). We should have mandatory and optional fields!
- validate the input and return proper error messages/http status
- log the input and output of each call and the processing time.
- have a request parameter which is not mandatory and which provides a default value in case is not set
- have a path variable
- clear code and javadoc
- only adults ( age &gt; 18 years)  and that live in France can create an account!

Bonuses:
- user a non-relational DB in order to save the users!
- use AOP
- documentation/UML/schemas to explain the architecture
