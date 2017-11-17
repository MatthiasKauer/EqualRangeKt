equal_range for Kotlin
----------------------

[![Build Status](https://travis-ci.org/MatthiasKauer/EqualRangeKt.svg?branch=master)](https://travis-ci.org/MatthiasKauer/EqualRangeKt)

Equal_range for Kotlin implements the three binary search functions from C++ for the JVM:

- [lowerBound](http://en.cppreference.com/w/cpp/algorithm/lower_bound) returns the index of the first element in a list `xs` that is *not less than* `x0`.
- [upperBound](http://en.cppreference.com/w/cpp/algorithm/upper_bound) returns the index of the first element in a list `xs` that is *greater than* `x0`.
- [equalRange](http://en.cppreference.com/w/cpp/algorithm/equal_range) returns `Pair(lower_bound, upper_bound)`. Typically, the implementation avoids running two binary searches.

The project currently provides two implementations: one with a focus on performance that was inspired by the
STL from C++ and a second one that trades some performance for shorter code.

### Goals

The main goal of this library is to have an equal_range implementation on the JVM.
To make the libary useful in various circumstances, the following properties are important.

1. **Tested**: The library needs testing so it can be used with confidence.
2. **Performant**: The implementation which was inspired by the C++ STL should be quite fast.
                   Nevertheless, I'm not a JVM expert (yet) and the performance there may be quite different.
                   Suggestions to improve the performance are welcome.
4. **Copyable**: This library should provide suitable code for copy-pasting.
                 In online environments, new libraries can typically not be included.
                 In order for your own code to remain the focus after you copy-paste,
                 the second implementation focuses on conciseness with roughly 20 lines of code.

### Why is this necessary?
By default, Java's and, by extension, Kotlin's binary search algorithm returns **the first item with equality**.
If you want to get the range of all items with equality, you are stuck writing your own code.
This is evidenced by questions like the following.

- [SO: Finding multiple entries with binary search](https://stackoverflow.com/questions/12144802/finding-multiple-entries-with-binary-search)

An alternative approach to the same problem would be using a multiset or multimap.
Such structures are available in Guava, for instance, but not in the standard libraries.
This route could be a good idea for a production project but it is infeasible in programming competitions
because such libraries are not available there.

### Todo

- [] Support custom comparators
- [] Upload as package
- [] Javadocs

### License
My code is under the [MIT license](LICENSE).

While writing parts of the code, I have been inspired by the implementation from the
["libc++" C++ Standard Library](https://libcxx.llvm.org/).
That project also allows the MIT license.
*Please contat me if a clearer attribution or a different license is needed*.
