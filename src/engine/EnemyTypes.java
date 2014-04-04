package engine;

import java.util.ArrayList;
import java.util.List;

public class EnemyTypes {
	private List<EnemyType> enemyTypes;

	public EnemyTypes() {
		enemyTypes = new ArrayList<EnemyType>();
	}

	public void add(EnemyType enemyType) {
		enemyTypes.add(enemyType);
	}

	public Enemy createEnemy(int type) {
		return enemyTypes.get(type).createEnemy();
	}
}
