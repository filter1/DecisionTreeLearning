Basic Idea.

- Create DecisionTree with root node.
- run the ID3 recursively on the root note. We adapted the algorithm. Instead of creating a ‘root’ node as dummy node in the first step of the original algorithm, the dummy node(s) are created when we are branching.
- To represent the tree: We have a class ‘Node’. It has a label, and a boolean value if it is a leaf. If it is a leaf, the label is the classification. If not, it is the an attribute and we are going to branch. The node also has a list of edges (if we are branching). In an edge, specify the regarding (newly created) child node. And we also store the value of the attribute, from which we are branching.
- To get a class of a sample, we are traversing from the root node to the ‘correct’ leaf.

We also added comments to parts of the source code to understand our thought process.

How to Run the Programm: Take the classes in ‘src’ and have fun. The car.date file is already in the folder. The result will be in the cardata.xml.

Team:
Franz Kuntke
Johannes Filter
