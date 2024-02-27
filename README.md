# mysterycompany

A simple demonstration of a self-modifying program in Clojure.

## Installation

Download from https://github.com/chipuni/MysteryCompany.

Then run by

    $ lein run

## Usage

This program demonstrates that Clojure allows self-modification.

Change the function in src/mysterycompany/selfoptimizing.clj named
complex-function to any complex function that takes three boolean
inputs and gives one boolean output.

Then run the program. It will (eventually) output the smallest
Clojure function using just the variables, `and`, `or`, and `not`.


## License

Copyright © 2024 Brent E Edwards

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
