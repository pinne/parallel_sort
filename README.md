Program Development in Functional and Object-oriented languages.
================================================================

What is this?
-------------
Implementation of merge sort and quicksort using the Fork/Join framework
(RecursiveAction) of Java 7.

Benchmark using the strategy pattern.

### Class diagram ###
![Sort class diagram](https://raw.github.com/pinne/sip_protocol/master/doc/class_diagram.png "UML class diagram for sort")

Usage
-----
    ant
    java com.douchedata.parallel.ParallelSort

Results
-------
    $ java com.douchedata.parallel.ParallelSort
    4 cores available
    Built-in sort:
    0 1 2 3 4 5 6 7 8 9         Time: 10304036

    Quicksort:
    0 1 2 3 4 5 6 7 8 9         Time: 12416955

    Parallel Quicksort:
    0 1 2 3 4 5 6 7 8 9         Time:  6714479

    Merge sort:
    0 1 2 3 4 5 6 7 8 9         Time: 18215156

    Parallel Merge sort:
    0 1 2 3 4 5 6 7 8 9         Time: 11670562

