# Effuse

A Clojure library designed to handle Dependancy Injection through mimicing Alegbraic Effects.

## Warnings
This library is still in alpha.
That means the core API can be changed at anytime.

Laziness, higher-order functions and threads might do not work properly because of the implementation using `^:dynamic` and `binding`.
You are expected to make all results eager before existing `handle`.
`bound-fn` and `bound-fn*` could be useful.

This library was inspired from https://lilac.town/writing/effects-in-clojure/ and https://github.com/clojureman/special

## Installation

Leiningen dependencies:
```
[effuse       "0.0.1"]
```

## Usage

```
(def effects-config
    {
        :save-data (partial spit "temp.txt") 
    })

(defn do-stuff []
  (->> 
    "test"
    (perform :save-data)))

(defn do-stuff-handled (handle do-stuff effects-config))

;; Saves to "temp.txt"
(do-stuff-handled)
```


## Why?

Algebraic effects have a lot of cool features like continuations or being able to go down multiple branches at once.
However, what interested me in them is that effectively they make Dependency Injection into a language construct.

Dependency Injection is broadly composed of two parts
- Lifecycle/State Management
- Implementation Injection

This framework focuses on Implementation Injection and NOT state management.
Libraries like 

Effuse has the following immediate goals...
- Exploration of Alegrbaic Effect like system for state management and side effects
- Minimall API - Two main functions `handle` and `perform`.
- Context/Scope aware - The context of how a function was called is relevant to how an effect happens. 
- No default implementation for `perform` - This is more a stylistic choice than a this-is-better choice. For you to call a function that uses `perform` you must `handle` what that effect does. Also makes it obvious if laziness is causing an issue.
- Run time configurable - Implied by the Context-awareness but listed here for explicitness

...and the following long-term goals...
- Use of Spec to validate effects are managed
- Minimally `invasive` - Should be similar to how most Clojure code is written. This is not totally true because of the lazy and other issues mentioned above.
- Support Laziness
- Support higher-order functions

## Other Libraries

https://github.com/clojureman/special

https://github.com/stuartsierra/component

https://github.com/tolitius/mount

https://github.com/weavejester/integrant


## Resources + Thoughts
https://overreacted.io/algebraic-effects-for-the-rest-of-us/

https://lilac.town/writing/effects-in-clojure/

https://en.wikipedia.org/wiki/Effect_system

https://en.wikipedia.org/wiki/Dependency_injection

https://medium.com/@kumarshantanu/dependency-injection-with-clojure-using-dime-af57b140bd3f

https://fsharpforfunandprofit.com/posts/dependency-injection-1/


## License

MIT
