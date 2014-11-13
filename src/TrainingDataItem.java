public class TrainingDataItem {
	String buying, maint, doors, persons, lug_boot, safety, targetClass;
	
	public TrainingDataItem(String str) {
		String[] a = str.split(",");
		this.buying = a[0];
		this.maint = a[1];
		this.doors = a[2];
		this.persons = a[3];
		this.lug_boot = a[4];
		this.safety = a[5];
		this.targetClass = a[6];
	}
}