# Gamecketlist

This application's purpose is to keep track of all the games that I wish to
play. I want to keep track of the Game's link, necessary equipment (such as a console),
some screenshots, it's price, it's genre. The reason for creating such an 
app is that video games can become pricey, and would get it if the game goes on sale
or decide to buy it in full price. The users of this particular application would
be not only me, but also for people who keep forgetting the games that they want
to play in the near future.

## User Stories
- ~~As a user, I want to be able to add an arbitrary amount Games into my list~~
- ~~As a user, I want to be able to see the number of games in my list.~~
- ~~As a user, I want to view the games in my list~~
- ~~As a user, I want to be able to modify the games in my list.~~
- ~~As a user, I want to see the average price of the games.~~
- ~~As a user, I want to see the number of games in each genre.~~
- ~~As a user, I want to be able to delete a game from my list.~~
- ~~As a user, I want to be able to save my GameList to a file (if I so choose)~~
- ~~As a user, I want to be able to be able to load my GameList from a file(if I so choose)~~

## Instructions for the Grader
- You can generate the first required action related to adding Xs to a Y by clicking on "Delete". It offers the functionality to delete a Game from the list.
- You can generate the second required action related to adding Xs to a Y by clicking on "View Game", and then searching for a filtered list of names of Game in a certain genre.
- You can locate my visual component by clicking on "Graph"
- You can save the state of my application by "Save to default"
- You can reload the state of my application by "Load from default"

## Phase 4: Task 2
    Sun Apr 09 19:40:25 PDT 2023 : Added Game with name test01
    Sun Apr 09 19:40:25 PDT 2023 : Added Game with name test02
    Sun Apr 09 19:40:25 PDT 2023 : Added Game with name test04
    Sun Apr 09 19:40:25 PDT 2023 : Added Game with name test04genre
    Sun Apr 09 19:40:25 PDT 2023 : Added Game with name addtest
    Sun Apr 09 19:40:33 PDT 2023 : Added Game with name event
    Sun Apr 09 19:40:52 PDT 2023 : Added Game with name genre
    Sun Apr 09 19:41:02 PDT 2023 : Added Game with name del
    Sun Apr 09 19:41:06 PDT 2023 : Deleted Game with name del
    Sun Apr 09 19:41:08 PDT 2023 : Calculated Total Price: 116.0
    Sun Apr 09 19:41:08 PDT 2023 : Calculated Average Price: 16.571428571428573
    Sun Apr 09 19:41:09 PDT 2023 : Calculated Total Price: 116.0
    Sun Apr 09 19:41:17 PDT 2023 : Searched for genre: event and found 2 games
    Sun Apr 09 19:41:24 PDT 2023 : Searched for genre: nogamesinthisgenre and found 0 games

## Phase 4: Task 3
Some aspects that I want to refactor my code is in the UI classes and in my GameList class.
My UI has a lot of frames that are its own individual classes where it might be helpful to have
an abstract class that allows them to be a simpler class rather than having long classes
that have some aspects that can be written in one single class. For example, each frame is 
initialized and the other elements to the frame are added afterwards, but instead of writing that in
many other classes, a single abstract class might have done a job reducing the amount of code
and increasing readability in my code.

Another aspect that I want to refactor is to change my GameList into a Singleton
Design Pattern or a static class. Every Frame in the UI needs access to the GameList, but
everytime the same GameList must be passed on to all the Frames, and this is troublesome since
I could have generated code where this might not have been the case. Therefore, I would have rater
made the GameList a static class or into a Singleton Design Pattern so that any class
that needs access to the GameList can do it in their own class without relying on any
other object having to pass the GameList into it.