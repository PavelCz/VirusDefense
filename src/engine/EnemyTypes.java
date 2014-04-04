package engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all enemy types. You can add a new enemy type. This Type will be saved at a specific index and later you can create an
 * Object with that number
 * 
 * @author Pavel
 */
public class EnemyTypes {
	private List<EnemyType> enemyTypes;

	public EnemyTypes() {
		this.enemyTypes = new ArrayList<EnemyType>();
	}

	/**
	 * @param enemyType
	 *            adds a new EnemyType to your EnemyTypes
	 */
	public void add(EnemyType enemyType) {
		this.enemyTypes.add(enemyType);
	}

	/**
	 * @param type
	 *            the number of the EnemyType
	 * @return return a new Enemy with the properties that the specified EnemyType has
	 */
	public Enemy createEnemy(int type) {
		return this.enemyTypes.get(type).createEnemy();
	}
}
