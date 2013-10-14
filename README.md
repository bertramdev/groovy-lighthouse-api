# Groovy Lighthouse API
The purpose of this project is to make the Lighthouse API accessible from Groovy.  Currently only supports GET requests.

## Why?
[Couldn't find one](https://www.google.com/search?q=groovy+lighthouse+api) and need it for a project.

## Examples
To use this API you need a Lighthouse URL and an API token.  You can signup for a free trial account [here](http://lighthouseapp.com/signup).

```
// Getting a list of your Lighthouse projects
import com.bertramlabs.lighthouse.Lighthouse

new Lighthouse(url: 'http://myapp.lighthouseapp.com', apiToken: '1212121212').projects()
```

```
// getting a list of tickets for a particular project
import com.bertramlabs.lighthouse.Lighthouse

new Lighthouse(url: 'http://myapp.lighthouseapp.com', apiToken: '1212121212').project(123).tickets()
```

## Repository Deployment
```build.gradle``` has the skeleton in place for deploying to a Nexus repository.  To facilitate deploying into any Nexus repository, create another Gradle file with the necessary details for that repository.  For this example we create a file called ```deploy.gradle```:

```
apply from: 'build.gradle'

uploadArchives {
	repositories {
		mavenDeployer {
			repository(url: 'http://nexus.yourcompany.com/content/repositories/releases') {
				authentication(userName: 'username', password: 'password')
			}
			snapshotRepository(url: 'http://nexus.yourcompany.com/content/repositories/snapshots') {
				authentication(userName: 'username', password: 'password')
			}
		}
	}
}
```

Then, deploy a new version: ```gradle -b deploy.gradle uploadArchives```

## Implementation
Every attempt is made to not retrieve any data unless it is necessary.  The example that retrieves the projects list does make an API request to get the list of projects, but the example where the tickets are retrieved from a project only gets the list of tickets.  Intermediate calls do not result in HTTP calls, i.e. the project Id is not validated.

## Todos
1. Reduce repetitive code.  API follows REST pretty well so a lot of the duplicate calls in each of the LH* classes can be meta'd.
1. Support creating/updating objects.  Only GET is currently supported.
1. Support non-token-authenticated calls, e.g. getting a list of users.
1. Incorporate into a Grails plugin in order to handle Lighthouse callbacks.
