package edu.cnm.deepdive.craps.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Game {

  private int pointValue;
  private State state;//keep up with its state
  private Random rng;
  // way to keep a roll of a pair of dice
  private List<int[]> rolls;
  private long wins;
  private long losses;

  public Game(Random rng){

    this.rng = rng;
    rolls = new LinkedList<>();
  }

  //need a reset method
  public void reset(){

    //reset state
    state = State.COME_OUT;// start at C state come out state
    pointValue = 0;
    rolls.clear();

  }

  private void roll(){


    int die0 = rng.nextInt(6)+1;
    int die1 = rng.nextInt(6)+1;
    int sum = die0+die1;
    State newState = state.change(sum,pointValue);
    if(state== state.COME_OUT && newState == State.POINT){

      pointValue = sum;
    }

    state = newState;
    int [] diceRoll = {die0,die1};
    rolls.add(diceRoll);
  }

  public State play(){
    //keep rolling until i have a win or loss
    while(state != State.WIN && state != State.LOSS){
      roll();
      if (state == State.WIN){

        wins++;
      }else if (state == State.LOSS){
        losses++;
      }
    }
    return state;

  }

  public int getPointValue() {
    return pointValue;
  }

  public State getState() {
    return state;
  }

  public List<int[]> getRolls() {
    List<int[]> copy = new LinkedList<>();//return a copy of my list
    for(int[] roll : rolls){

      copy.add(Arrays.copyOf(roll,roll.length));
    }
    return copy;
  }

  public long getWins() {
    return wins;
  }



  public long getLosses() {
    return losses;
  }


  }

