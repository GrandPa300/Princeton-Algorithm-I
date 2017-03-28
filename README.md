## Project Assignments

#### Week 1: Percolation 

<div align=center>
    <img src="http://coursera.cs.princeton.edu/algs4/assignments/percolates-yes.png"
	     width="30%" height="30%" />
</div>

   * To model a percolation system by using UNION FIND data sutrcture.  
   * To perform a series of Monte Carlo simulation.  
   * **Challenge:** to solve "back wash" problem with single UNION FIND object.
   * Please check [specification](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html) and [checklist](http://coursera.cs.princeton.edu/algs4/checklists/percolation.html) for more details. 

#### Week 2: Deque and Randomized Queues  

   * Create a double-ended queue or deque that supports adding and removing items from either the front or back of the data structure. 
   * Create a randomized queue that item removed is *chosen uniformly at random*.
   * **Challenge:** use only one `Deque` or `RandomizedQueue` object of maximum size at most *k*.
   * An example of Permutation client outputis shown as following:

```
% more distinct.txt                        % more duplicates.txt
A B C D E F G H I                          AA BB BB BB BB BB CC CC

% java Permutation 3 < distinct.txt       % java Permutation 8 < duplicates.txt
C                                          BB
G                                          AA
A                                          BB
                                           CC
% java Permutation 3 < distinct.txt        BB
E                                          BB
F                                          CC
G                                          BB
```  

   * Please check [specification](http://coursera.cs.princeton.edu/algs4/assignments/queues.html) and [checklist](http://coursera.cs.princeton.edu/algs4/checklists/queues.html) for more details. 

