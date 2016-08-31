## dummy project

This projects provides a starting point for your own backend _akka_ and 
frontend _play_ projects. 

Follow these steps to get started:

1. Git-clone this repository.

        $ git clone https://github.com/retriku/the-code.git my-project

2. Change directory into your clone:

        $ cd my-project

3. Launch backend using SBT:

        $ sbt 'project backend' 'run' &

4. Browse to http://localhost:8080/

5. Launch frontend using SBT:

        $ sbt 'project frontend' 'start' &

6. Browse to http://localhost:9090/

7. You can also run both apps
        
        $ ./start.sh
