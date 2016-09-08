[ ![Codeship Status for PuspenduBanerjee/ScalaAkkaBlah](https://codeship.com/projects/b1b00720-3cf8-0134-9c43-06578867a321/status?branch=master)](https://codeship.com/projects/167081)
# ScalaAkkaBlah
A space for myself to learn and share more on Scala, Akka and related paradigm.
It's not a product or anything of that kind. It's pureplay ScratchPad :P

# What has been covered:
1.  Akka Actor Model
       * Stateless
2.  Scala Test
3.  Spray Test -- Decoupled webservice testing with Spray's English like Descriptive  DSL
4.  Akka Testkit -- Test your Actors 
5.  Added Camel Route for Http Interface via Jetty exploiting akka-camel Actor. rest-->jetty-->camel-->akka


Notes on cxf camel integration:
    Service Base: http://localhost:18878/CxfRsRouterTest/route
    - REST WADL http://localhost:18878/CxfRsRouterTest/route?_wadl
    - REST SWAGGER http://localhost:18878/CxfRsRouterTest/route/swagger.json|yaml
    - REST SWAGGER UI http://localhost:18878/CxfRsRouterTest/route/api-docs?/url=/CxfRsRouterTest/route/swagger.json
    
    