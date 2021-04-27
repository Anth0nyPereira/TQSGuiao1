Task 1

1e/ Access the SonarQube dashboard (default : http://127.0.0.1:9000). Has your project passed the defined quality gate? Elaborate your answer.
- Yes, the project passed the defined quality gate.


1.f/ Explore the analysis results and complete with a few sample issues, as applicable. (Place your response in a Readme document/markdown file, along with the code project).

Issue               Problem description                         How to solve

Bug                 Save and re-use this "Random"               Create a single random, store it and reuse it; You should not create a Random object each timer you need a random value

Security Hotspots   Weak Cryptography - Random                  Use "java.security.SecureRandom" instead of Random

Code Smell (major)  Refactor loop code                          Refactor the code in order to not assign to this loop counter from within the loop body

Code Smell (major)  Replace System.out                          Replace this use of System.out or System.err by a logger
