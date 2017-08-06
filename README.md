## Project Assignments
### 1: Percolation 

<div align=center>
    <img src="http://coursera.cs.princeton.edu/algs4/assignments/percolates-yes.png"
       width="50%" height="50%" />
</div>

   * Model a percolation system by using `UNION FIND` data structure.
   * Perform a series of Monte Carlo simulation. 
   * Please check [instruction](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html) and [checklist](http://coursera.cs.princeton.edu/algs4/checklists/percolation.html) for more details. 
#### Implementation and Optimization:
* __version 1__: Virtual top/ bottom blocks are created to avoid redundant search in top/bottom row. 
	* Problem: Virtual bottom, once it was percolated, would cause "back wash" bug as below:

<div align=center>
<img src = "http://coursera.cs.princeton.edu/algs4/checklists/percolation-backwash.png" width="50%" height="50%" />
</div>

* __version 2__: Use two separate `UNION FIND` objects - one same as version 1 to determine percolation, and another without virtual bottom to determine if a block is filled.
* __version 3__: Virtual blocks are no longer used. Only one `UNION FIND` object is used to record the root of each block.
	*  For each block, check itself and any of its neighbors are connected to top/bottom
	*  Two additional boolean arrays are used to record its top/ bottom connection status. 
	*  If there's a block have `TRUE` status in both array, the whole system is percolated. 
### 2: Deque and Randomized Queues  
   * Create a double-ended queue or deque that supports adding and removing items from either the front or back of the data structure. 
   * Create a randomized queue that item removed is __chosen uniformly at random__.
   * Create a Permutation client which takes a command-line integer k and prints out exactly k of them. 
	   * Result is uniformly at random, and prints each item from the sequence at most once. Example output is shown as following:

<div align=center>
<img src = "http://i.imgur.com/tW857Cv.jpg">
</div>

   * Please check [instruction](http://coursera.cs.princeton.edu/algs4/assignments/queues.html) and [checklist](http://coursera.cs.princeton.edu/algs4/checklists/queues.html) for more details. 
#### Implementation and Optimization:
* __version 1__: a doubly linked list is used to implement `Deque`, and array is used for Randomized Queue. Array size will be doubled when full.
* __version 2__: 
	* use a sentinel node that connects both head and tail of `Deque` to simplify code. 
	* Array size will be halved if array is less than quarterly-full. 
	* Minimum size of array is set to 8 to avoid unnecessary size-shirking.  
	*  For Permutaion.java, in order to use only one `Deque` or `RandomizedQueue` object of maximum size at most _k_, input string is shuffled first, then put only first k items into `Deque`. 
### 3: Pattern Recognition - Collinear Points
*  Write `BruteCollinearPoints` that examines 4 points at a time and checks whether they all lie on the same line segment. __O(N^4)__

<div align=center>
    <img src="http://coursera.cs.princeton.edu/algs4/assignments/lines2.png"
       width="50%" height="50%" />
</div>

*  Write `FastCollinearPoints` which does the same as above and return all such line segments.  __O(NLogN * N)__
	*  To check whether the 4 points p, q, r, and s are collinear, just check whether three slopes p-q, p-r, and p-s are all equal.

 <div align=center>
  <img src="http://coursera.cs.princeton.edu/algs4/assignments/lines1.png"
     width="25%" height="25%" />
 </div>
 
 * Please check [instruction](http://coursera.cs.princeton.edu/algs4/assignments/collinear.html) and [checklist](http://coursera.cs.princeton.edu/algs4/checklists/collinear.html) for more details.  
#### Implementation and Optimization:
*  __Version 1__: `FastCollinearPoints`: 
	*  sort points based on x-y, go through each point *p*
    * For each other point *q*, determine the slope it makes with *p*, and sort  again based on its slope with *p*.
    * Since `Merge Sort` is stable, points will grouped by slopes and in each slop still  sorted by x-y.
    * Sweeping two pointers through, check if any 3 (or more) adjacent points have equal slopes with *p*.
* __Version 2__: Fix bug
	* To include only maximal segment, segment need to fulfill `p.compareTo(pFirst) < 0`. 
	* i.e. *p* itself need to be the smallest point in the same slope group. 
### 8-puzzle
* Write a `Board` to represent status of game board status. 
* Write a `Solver`  to solve the 8-puzzle problem (and its natural generalizations) using the A* search algorithm.

 <div align=center>
  <img src="http://i.imgur.com/3MQtrp2.jpg">
 </div>
 
* Please check [instruction](http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html) and [checklist](http://coursera.cs.princeton.edu/algs4/checklists/8puzzle.html) for more details.  
#### Implementation and Optimization:
* a `Minimum Priority Queue` is the data structure, and its priority is based on board's Manhattan distance. 

<div align=center>
  <img src="http://i.imgur.com/5Zkp3ov.jpg">
 </div>
 
 * __Version 1__:
	 *  To detect unsolvable board and avoid TLE (never reaches goal board and exits while-loop), a `SearchNode` of twin board is also inserted into MinPQ. If a target board is reached by a Twin node, original board is not solvable. 
	 * `SearchNode` uses variable  `prevNode` to avoid unnecessary search (i.e. going back and forth between 2 neighboring boards).
 *  __Version 2__:
	 *  Use two individual MinPQ for original board and twin board. So instead searching alternatively between type of Nodes, now the best-first searching is simultaneous. The searching ends whichever reached the goal board. 




