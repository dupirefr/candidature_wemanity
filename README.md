# Wemanity Job Application - François Dupire - Technical Test
## Run the Code
First, run `./gradlew build` in a terminal (in the project directory). It'll compile sources
and test sources, as well as run the tests and produce a `.jar` file.

The test report can be found under `build/reports/tests/test`, and the `.jar` file under `build/libs`.

In order to execute the program on a fake store, run `java -jar build/libs/gilded-rose-kata-0.0.1-SNAPSHOT.jar`

## Work Methodology
I followed a few practices while doing this test.

First of all, I used TDD all along, first to increase the test coverage of the application, then to cover
the new feature (once I refactored the rest of the code). This way I ensured my modifications didn't alter the
behaviour of the application.

Then, I tried to make my commits as small as possible, running the tests all along (maybe each two to three commits, max).
This way I could manage failure easier, rolling back to a previous version of the code without loosing a lot of modifications.

Usually I squash all my commits in a few ones, in order to make a possible revert easier. Here, I let the entire history
as-is, but if I were to squash the commits, I would do it into three commits (aside from the "technical" ones like the first and two lasts):
* Initial test coverage
* Refactoring
* Implementation of the new feature

You can already see that structure in the history, each commit message preceded by an asterisk would denote a commit
to be squashed.

## Architectural Decisions
As for the architecture of the project, I proceeded in a few phases:
* First, I tried to make things clearer by extracting methods instead of using direct comparisons and stuff.
That made the code easier to read and understand, and factored some redundant code along the way.
* Then, did my best to rearrange the code in a fashion that made the future class hierarchy pop out.
* Finally, I created the class hierarchy. Unfortunately the `Item` class is not modifiable, therefore I was obligated
to externalize that hierarchy. But, in my opinion, specific `Item` descendant like `AgedBrie` or `BackstagePasses` would've
made more sense in an Object-Oriented paradigm.