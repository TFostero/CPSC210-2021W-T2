# Rocket Trajectory Calculator

This application will calculate the trajectory or a test rocket so that the testers will have an approximate idea of
where the rocket will land so that it can be retrieved.

The testers would like to be able to input a different fuel mass, thrust force, and launch angle each time in order to
test variations of the rocket.

Calculated fight path will include:
- **X** and **Y** rocket position relative to launch site over time
- **X** and **Y** rocket velocity over time
- **X** and **Y** rocket acceleration over time
- Rocket flight angle over time

I chose this project because I'd like to be able to create another version in the future that can be used to create a 
game to try to launch rockets into orbit.
 

## User Stories
- as a user, I'd like to be able to input the fuel mass, thrust force, and launch angle prior to running the calculation
- as a user, I'd like to be able to configure as many tests as I want which will run in sequence
- as a user, I'd like to be able to see the rockets position, velocity, acceleration, and flight angle over time
- as a user, I'd like to have to option to save the rockets I've created before launching them
- as a user, I'd like to be able to load the rockets that I previously saved before they were launched


## Phase 4: Task 2
Mon Mar 28 10:10:36 PDT 2022
Rocket Test Rocket added to launch pad.

Mon Mar 28 10:10:38 PDT 2022
Rocket Test Rocket launched.

If nothing is output when the program closes it could indicate that no rockets were added to the program and
that no rockets were loaded and launched. It could also be caused by something going wrong that causes the function that
prints the log to console not being run when the program is closed.