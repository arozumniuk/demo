
#Welcome


**#For set up environment required**

- java
- gradle
- allure
- Download project to your machine

**#For running tests via command line do the following:**
- Open terminal, navigate to project's directory, execute command 
```
gradle clean apiTests uiTests
```

Report will be added to {project directory}/build/html/index.html


**#Web-page with report:**

- after tests will be finished execute:

```
allure serve <{project directory}>/allure-results
```
