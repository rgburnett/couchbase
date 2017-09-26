#Couchbase NiQL Input Sanitisation#
  
##Introduction##

This java library will validate input to protect against NiSQL
injection attacks. It should be used to validate all input to
server-side applications and APIs

It use two techniques: regular expressions and the Aho-Corasick
Trie which is used to look for keywords in large sets.

##Design##

- A standard regex approach is taken for NiQL injection detection
  of the two most common attacks (comment and 1 = 1).
- The second technique looks for an adjustable threshold of NiQL
  keywords (default 3) in the input. This is extremely fast and
  lightweight. It
  is also lazy but the balance towards performance beats the kkkjj

##Future Development##

An XACML driven NiQL parser to dynamically analyse and modify NiQL
queries based on XACML specifications for a Role (RBAC.)

Range Checking per field based on XACML configuration

Configuration sourcing from Hashicorp's Consul.

##Author##

Graeme.Burnett@acm.org
