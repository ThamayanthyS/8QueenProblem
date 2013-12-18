package algo;

public class Simmulater {

	private static final int LAST_BOARD_SQUARE = 63;
	private static final int FIRST_BOARD_SQUARE = 0;
	private static final int MAX_POSSIBLE_ATTACKS = 28;

	private static final int NUM_QUEENS = 8;
	private static final int NUM_BOARD_SQUARES = 64;
	private double currentSystemTemperature;
	private double freezingTemperature;
	private int currentSystemEnergy;
	private int[][] currentQueensPositions;
	private double coolingFactor;
	private double stabilizingFactor;
	private int[][] newQueensPositions;
	double currentStabilizer;
	boolean[][] currentboard;

	public boolean[][] getCurrentboard() {
		return currentboard;
	}

	public void setCurrentboard(boolean[][] currentboard) {
		this.currentboard = currentboard;
	}

	public double getCurrentSystemTemperature() {
		return currentSystemTemperature;
	}

	public void setCurrentSystemTemperature(double currentSystemTemperature) {
		this.currentSystemTemperature = currentSystemTemperature;
	}

	public double getFreezingTemperature() {
		return freezingTemperature;
	}

	public void setFreezingTemperature(double freezingTemperature) {
		this.freezingTemperature = freezingTemperature;
	}

	public int getCurrentSystemEnergy() {
		return currentSystemEnergy;
	}

	public void setCurrentSystemEnergy(int currentSystemEnergy) {
		this.currentSystemEnergy = currentSystemEnergy;
	}

	public int[][] getCurrentQueensPositions() {
		return currentQueensPositions;
	}

	public void setCurrentQueensPositions(int[][] currentQueensPositions) {
		this.currentQueensPositions = currentQueensPositions;
	}

	public double getCoolingFactor() {
		return coolingFactor;
	}

	public void setCoolingFactor(double coolingFactor) {
		this.coolingFactor = coolingFactor;
	}

	public int[][] getNewQueensPositions() {
		return newQueensPositions;
	}

	public void setNewQueensPositions(int[][] newQueensPositions) {
		this.newQueensPositions = newQueensPositions;
	}

	public double getCurrentStabilizer() {
		return currentStabilizer;
	}

	public void setCurrentStabilizer(double currentStabilizer) {
		this.currentStabilizer = currentStabilizer;
	}

	public Simmulater() {
		coolingFactor = 0.001;
		stabilizingFactor = 1.05;
		freezingTemperature = 0;
		currentSystemEnergy = 100;
		currentSystemTemperature = 0.5;
		currentStabilizer = 100;
		currentQueensPositions = new int[NUM_QUEENS][2];
		newQueensPositions = new int[NUM_QUEENS][2];
		// System.out.println("pppppp");
		for (int i = 0; i < 8; i++) {
			currentQueensPositions[i][0] = i;
			currentQueensPositions[i][1] = i;
			// System.out.println(currentQueensPositions[i][0]+""+currentQueensPositions[i][1]);
			// print();

		}

		// generateNewSolution = null;
		// generateNeighbor = null;
		// acceptNeighbor = null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Simmulater s = new Simmulater();
		// s.generateRandomPositions();
		// for (int i = 0; i < 100; i++) {
		// s.doSimulationStep();
		// s.generateNeighbor();
		// s.acceptNeighbor();
		boolean b = false;
		int x = 0;
		while (s.currentSystemEnergy > 0) {
			System.out.println(s.currentSystemTemperature);
			while (b == false) {
				b = s.doSimulationStep();
				// System.out.println(x++);
				x++;
			}
			s.currentSystemTemperature+=0.5;
			s.print();
			System.out.println();
			System.out.println(s.currentSystemEnergy + " " + x);
		}
		// }

		// s.print();

		// TODO Auto-generated method stub

	}

	public boolean probabilityFunction(double temperature, double delta) {
		if (delta < 0) {
			return true;
		}

		double C = Math.exp(-delta / temperature);
		double R = Math.random();

		if (R < C) {
			return true;
		}

		return false;
	}

	public boolean doSimulationStep() {
		if (currentSystemTemperature > freezingTemperature) {
			// int currentStabilizer;
			for (int i = 0; i < currentStabilizer; i++) {
				int newEnergy = generateNeighbor();
				int energyDelta = newEnergy - currentSystemEnergy;

				if (probabilityFunction(currentSystemTemperature, energyDelta)) {
					acceptNeighbor();
					currentSystemEnergy = newEnergy;
				}
				if (currentSystemEnergy == 0) {
					return true;
				}
				// if(newEnergy<currentSystemEnergy){
				// acceptNeighbor();
				// currentSystemEnergy=newEnergy;
				// }
			}
			currentSystemTemperature = currentSystemTemperature - coolingFactor;
			currentStabilizer = (currentStabilizer * stabilizingFactor);
			return false;
		}
		currentSystemTemperature = freezingTemperature;
		return true;
	}

	public int calculateAttacks(int[][] board) {
		int numAttacks = 0;

		for (int iQueen = 0; iQueen < NUM_QUEENS - 1; iQueen++) {
			for (int iAttackingQueen = iQueen + 1; iAttackingQueen < NUM_QUEENS; iAttackingQueen++) {
				if (board[iQueen][0] == board[iAttackingQueen][0]) {
					numAttacks++;
				} else if (board[iQueen][1] == board[iAttackingQueen][1]) {
					numAttacks++;
				} else if (board[iQueen][0] + board[iQueen][1] == board[iAttackingQueen][0]
						+ board[iAttackingQueen][1]) {
					numAttacks++;
				} else if (board[iQueen][1] - board[iQueen][0] == board[iAttackingQueen][1]
						- board[iAttackingQueen][0]) {
					numAttacks++;
				}
			}
		}
		return numAttacks;
	}

	// Generate 8 queens with random (x, y) and no repetitions. No queen can
	// share the same space as another one.
	public int generateRandomPositions() {
		boolean done = false;

		for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
			boolean repetitions = true;

			// currentQueensPositions[iQueen] = {};
			while (repetitions) {
				currentQueensPositions[iQueen][0] = (int) (Math.random() * 8);
				currentQueensPositions[iQueen][1] = (int) (Math.random() * 8);

				if (checkRepetitions(currentQueensPositions) == false) {
					repetitions = false;
				}
			}
		}

		return calculateAttacks(currentQueensPositions);
	}

	// Take our current solution and generate a neighbor solution by taking a
	// random queen and moving it a single random step.
	private boolean checkRepetitions(int[][] board) {

		int howMany = board.length;

		for (int iQueen = 0; iQueen < howMany - 1; iQueen++) {
			for (int iCheckQueen = iQueen + 1; iCheckQueen < howMany; iCheckQueen++) {
				if (board[iQueen][0] == board[iCheckQueen][0]
						&& board[iQueen][1] == board[iCheckQueen][1])
					return true;

			}
		}

		return false;

	}

	public int generateNeighbor() {
		for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
			// int[][] newQueenPositions=new int[NUM_QUEENS][2];
			// System.out.println(currentQueensPositions[0][0]);
			newQueensPositions[iQueen][0] = currentQueensPositions[iQueen][0];
			newQueensPositions[iQueen][1] = currentQueensPositions[iQueen][1];
			// System.out.println(newQueensPositions[0][0]);

		}

		int changingQueen = (int) (Math.random() * NUM_QUEENS);
		boolean repetitions = true;

		while (repetitions) {
			int oldX = newQueensPositions[changingQueen][0];
			int oldY = newQueensPositions[changingQueen][1];

			// The MOD is so if a queen moves out of the board it comes through
			// the other side. The extra mod logic is to avoid a javascript bug
			// that makes negative numbers mod into -1.
			newQueensPositions[changingQueen][0] = (int) ((((newQueensPositions[changingQueen][0] + ((Math
					.random() * 3) - 1)) % 8) + 8) % 8);
			newQueensPositions[changingQueen][1] = (int) ((((newQueensPositions[changingQueen][1] + ((Math
					.random() * 3) - 1)) % 8) + 8) % 8);

			if (checkRepetitions(newQueensPositions) == false) {
				repetitions = false;
			} else {
				newQueensPositions[changingQueen][0] = oldX;
				newQueensPositions[changingQueen][1] = oldY;
			}
		}

		return calculateAttacks(newQueensPositions);
	}

	// Accept the current generated solution.
	public void acceptNeighbor() {
		for (int iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
			currentQueensPositions[iQueen][0] = newQueensPositions[iQueen][0];
			currentQueensPositions[iQueen][1] = newQueensPositions[iQueen][1];

		}
	}

	// var Queens = (function () {
	//
	//
	// currentQueensPositions = [],
	// newQueensPositions = [];
	//
	// function _generateRandomPositions () {
	// var done = false;
	//
	// for (var iQueen = 0; iQueen < NUM_QUEENS; iQueen++) {
	// var repetitions = true;
	//
	// currentQueensPositions[iQueen] = {};
	// while (repetitions) {
	// currentQueensPositions[iQueen].x = parseInt(Math.random() * 8);
	// currentQueensPositions[iQueen].y = parseInt(Math.random() * 8);
	//
	// if (!_checkRepetitions(currentQueensPositions)) {
	// repetitions = false;
	// }
	// }
	// }
	//
	// return _calculateAttacks(currentQueensPositions);
	// }
	public void print() {
		boolean[][] currentboard = new boolean[8][8];
		for (int i = 0; i < 8; i++) {
			currentboard[currentQueensPositions[i][0]][currentQueensPositions[i][1]] = true;
		}
		for (int j = 0; j < 8; j++) {
			System.out.println();
			for (int k = 0; k < 8; k++) {
				if (currentboard[j][k] == true)
					System.out.print(" 1");
				else
					System.out.print(" 0");
			}
		}
	}

}