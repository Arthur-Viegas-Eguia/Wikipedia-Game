# Six Degrees of Wikipedia Game
This project was implemented as a homework for Data Structures taight by Anya Vostinar.
## Overview:
The program models a game where a user starts from a a Wikipedia article and tries to get to another specific wikipedia article by using links present in the articles in as few articles as possible. Here is a [Wikipedia article](https://en.wikipedia.org/wiki/Wikipedia:Six_degrees_of_Wikipedia) about it. 
This program finds the shortest path connecting two given articles within a specific user provided dataset. With this program you can:
- Choose all the articles considered by the program via an external file;
- Communicate all the links present in each article and where they lead to to the program via an external file;
- Choose which article you want to start at and which article you want to arrive at via runtime input;
- Having the shortest path between the start article and the destination article automatically calculated and printed on the screen;
- Being told if no path between the articles you choose exist, if that is the case.
## Usage: 
To use the program, you have to compile and run the code after downloading. To do this, simply
type in the terminal:
```
javac *.java
java PathFinder
```
The program will start by asking for the list of all articles considered by the program, this list should follow the following program:
```
Write_title_of_article
Spaces_are_replaced_by_underscore
One_article_per_line
```
The program will also ask for each link present in every article and where they lead to via an external file. It must have the name of the original article (the article containing the link) in the beginning
of the line and the article the links points to right after, separated by a space, see example:
```
a b
b c
```
This shows that article a has link to article b, and article b has link to article c.

### Warning:
This code has been developed on a MAC, the testing datasets articles.tsv and links.tsv may not work on other operational systems. If using on windows please make sure to have a functional dataset.

