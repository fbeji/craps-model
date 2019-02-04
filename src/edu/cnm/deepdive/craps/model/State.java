package edu.cnm.deepdive.craps.model;

/**
 * <code>State</code> implements a basic state machine for the main play of a
 * game of Craps, starting with the come-out roll, and ending with a win or loss
 * of the main bet.
 *
 * @author Nicholas Bennett &amp; Deep Dive Coding Java + Android cohort 6
 * @version 1.0
 */
public enum State {
  COME_OUT {
    @Override
    public State change(int roll, int pointValue) {
      switch (roll) {
        case 2:
        case 3:
        case 12:
          return LOSS;
        case 7:
        case 11:
          return WIN;
        default:
          return POINT;
      }
    }
  },
  POINT {
    @Override
    public State change(int roll, int pointValue) {
      if (roll == 7) {
        return LOSS;
      }
      if (roll == pointValue) {
        return WIN;
      }
      return this;
    }
  },
  WIN,
  LOSS;

  /**
   * Applies the specified roll sum to this state, returning the state resulting
   * from the transition represented by the roll. For the terminal states ({@link
   * #WIN} and {@link #LOSS}), no change of state will result from any roll sum
   * value.
   *
   * @param roll        sum of dice in current roll.
   * @param pointValue  established point value (ignored unless this state is
   *                    {@link #POINT}).
   * @return            state resulting from transition.
   */
  public State change(int roll, int pointValue) {
    return this;
  }

}

