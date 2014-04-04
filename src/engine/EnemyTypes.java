package engine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Handles all enemy types. You can add a new enemy type. This Type will be saved at a specific index and later you can
 *         create an Object with that number
 */
public class EnemyTypes {
	private List<EnemyType> enemyTypes;

	public EnemyTypes() {
		enemyTypes = new ArrayList<EnemyType>();
	}

	/**
	 * @param enemyType
	 *            adds a new EnemyType to your EnemyTypes
	 */
	public void add(EnemyType enemyType) {
		enemyTypes.add(enemyType);
	}

	/**
	 * @param type
	 *            the number of the EnemyType
	 * @return return a new Enemy with the properties that the specified EnemyType has
	 */
	public Enemy createEnemy(int type) {
		return enemyTypes.get(type).createEnemy();
	}
}
