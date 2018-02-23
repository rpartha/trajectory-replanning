# CS440: Assignment 1 Write-Up
Ramaseshan Parthasarathy, Benjamin Yan, Vamshikrishnan Balakrishnan

## Part 1

1. In figure 8, the agent would move east instead of north, because the cell east of the agent is closer to the goal than the cell north of the agent. If we are computing h(x) using Manhattan distances, the h(x) value for the cell east of the agent is 3. While the h(x) for the cell north of the agent is 4. If we are computing h(x) using straight-line distances the h(x) value for the cell east of the agent is 3. And the h(x) value of the cell north of the agent is sqrt(17), which is approximately 4.123. The g(x) values for the cell to the east of the agent and the cell to the north of the agent are 1 and 1 respectively. Therefore, if we are to compute h(x) using Manhattan distances the f(x) value of the cell to the east of the agent is 4 and the f(x) value of the cell to the north of the agent is 5. And if we are to compute h(x) using straight-line distances, the f(x) value of the cell to the east of the agent is 5, and the f(x) value of the cell to the north of the agent is 5.123. So no matter what you are using to compute h(x), the cell to the east of the agent is always preferable to the cell north of the agent, since the f(x) value of the cell east of the agent is always less than the f(x) value to the north of the agent.

2. If we visualize A* search as a tree, and let all of the nodes of the tree be the cells of the environment, we would have a tree with a finite number of nodes. Cells are represented as nodes and their connections to their neighbors are represented by edges. The algorithm will be traversing the tree based on the smallest f(x) value among the nodes in the open list. In the worst case, we would have to traverse the whole tree before reaching the goal node. Since there are a finite number of nodes, that means that there are a finite number of blocked nodes. Therefore, in a finite amount of time, the algorithm should be able to determine if the search is possible or not, given that it has exhausted all possible paths. Given an n x n gridworld, a move consists of moving from one unblocked cell to another unblocked cell. Let m be defined as the number of unblocked cells in the gridworld, where $2\leq m\leq n$. The maximum number of times the agent can visit a particular unblocked cell is 4. The agent must initially reach an unblocked cell from either the north, south, east, or west, given that the cell does not lie on an edge of the grid. Regardless of what direction the agent reaches the unblocked cell, there are now 3 possible directions for the agent to travel. Assume the worst case in which the immediate neighbors of the agent are unblocked but each path results in a dead end, which causes the agent to backtrack. There will be a total of 3 backtracks plus 1 initial visit, which results in 4 total visits to an unblocked cell. If we upperbound the number of maximum visits to an unblocked cell to 4, then we have a maximum number of 4*m possible moves. Even if the agent were to make the maximum possible number of moves by backtracking to every cell it has visited 3 times, m2 will be an upperbound to the number of moves an agent can make for $m\geq6$. $m\geq6$ for backtracking in three directions to take place, 2 cells for agent and target, and 4 for the neighbors of the agent. For $2\leq m\leq5$, we must consider circumstances where the agent has less than 4 neighbors. For the case where m = 2, if the target is a neighbor of the agent, then it will take one move to reach the target. Otherwise, it will take 0 moves to discover that the task is impossible. Therefore, 4 serves as a viable upperbound in this situation. For the case where m = 3, the agent can reach the target in one move if the target is a neighbor of the agent or in two moves if the agent moves to an unblocked cell and then to the target. In the case where the task is impossible, the agent will have moved a maximum of once. Therefore, 9 serves as a viable upperbound in this situation. For the case where m = 4, the agent can reach the target in either 1, 2 or 3 moves. If the target is a neighbor of the agent, then it will take the agent 1 move to reach the target. If the agent moves to an unblocked cell and then to the target, the agent moves twice. If the agent moves to an unblocked cell and then to another unblocked cell and then to the target, the agent will have moved thrice. It takes the agent a maximum of two moves to discover that the task is impossible. Therefore, 16 serves as a viable upperbound in this situation. For the case where m = 5, the agent can reach the target in either 1,2,3, or 4 moves. If the target is a neighbor of the agent, then it will take the agent 1 move to reach the target. If the agent moves to an unblocked cell and then to the target, the agent moves twice. If the agent moves to an unblocked cell and then to another unblocked cell and then to the target, the agent will have moved thrice. If the agent moves to an unblocked cell then to another unblocked cell then to another unblocked cell and then to the target, the agent will have moved four times. It takes the agent a maximum of three moves to discover that the task is impossible. Therefore, 25 serves as a viable upperbound in this situation. In all cases $2\leq m\leq n$, m<sup>2</sup> serves as a viable upperbound. 

## Part 2

### Breaking Ties (smaller g-values)

Trial 1: 1007907728 ns
Trial 2: 1455872540 ns
Trial 3: 1439560585 ns
Trial 4: 956870541 ns
Trial 5: 1064401799 ns
Trial 6: 8854319000 ns
Trial 7: 1233440049 ns
Trial 8: 835073757 ns
Trial 9: 1432089754 ns
Trial 10: 1213176520 ns
On Average: 1949271227 ns

### Breaking Ties (larger g-values)

Trial 1: 775270947 ns
Trial 2: 1287598377 ns
Trial 3: 1058307391 ns
Trial 4: 1652876540 ns
Trial 5: 847576372 ns
Trial 6: 1004614719 ns
Trial 7: 1068484403 ns
Trial 8: 870354068 ns
Trial 9: 906583240 ns
Trial 10: 1219061826 ns
On Average: 1069072788 ns

Breaking ties in favor of larger g values is on average roughly two times faster than breaking ties in favor of smaller g values.

## Part 3

### Repeated Forward A<sup>*</sup>

Trial 1: 873079900 ns
Trial 2: 1034859163 ns
Trial 3: 1122366675 ns
Trial 4: 781828665 ns
Trial 5: 978880223 ns
Trial 6: 975576218 ns
Trial 7: 977225415 ns
Trial 8: 2466504808 ns
Trial 9: 750320731 ns
Trial 10: 1207171370 ns
On Average: 1028680987 ns

### Repeated Backward A<sup>*</sup>

Trial 1: 1331522895 ns
Trial 2: 1160061981 ns
Trial 3: 1053726236 ns
Trial 4: 922918181 ns
Trial 5: 1042151044 ns
Trial 6: 925549205 ns
Trial 7: 1108425774 ns
Trial 8: 1455896576 ns
Trial 9: 1184367723 ns
Trial 10: 1190291375 ns
On Average: 1137491099 ns

Repeated Forward A* is on average a little faster than Repeated Forward A*. The rates are pretty much the same because the two algorithms are identical except for the fact that the starting position of the agent is swapped with the starting position of the target. Given any grid, going from agent to target is going to take the same time as going from target to agent. Therefore the running times of Repeated Forward A* are going to be the same as Repeated Backward A* if they are working on the same grid. If they are working on different grids, then their running times will be close to one another.

## Part 4

A heuristic function is said to be consistent if for any states n and n’ and any action a:
**$h(n) \leq c(n,a,n’) + h(n’)$**, where c(n, a, n’) is the step cost for going from n to n’ using action a. 

Given any two states on the grid, one can compute the Manhattan distances between the two states. Suppose that moving from n to n’ results in the agent moving farther away from the target. Then c(n, a, n’) will be the Manhattan distance between n and n’ and h(n’) will be the Manhattan distance from n’ to the goal. Notice that $h(n) \leq h(n’)$. Also c(n, a, n’) is always greater than 0. Therefore, that implies that $h(n) \leq c(n,a,n’) + h(n’)$. Now suppose that moving the agent from n to n’ results in bringing the agent closer to the target. c(n, a, n’) will once again denote the Manhattan distance between n and n’. In this case $h(n’) \leq h(n)$. However, the amount by which h(n’) is less than h(n) is exactly c(n, a, n’). So then $h(n) = c(n, a, n’) + h(n’)$, which means that $h(n) \leq c(n,a,n’) + h(n’)$  holds. This can only be guaranteed if the agent can only move in the four main compass directions: north, south, east, west. If the agent were able to move diagonally then in some cases $h(n) \neq c(n, a, n’) + h(n’)$, since h(n) and h(n’) will differ by more than c(n, a, n’), in which case $h(n) > c(n,a,n’) + h(n’)$.







