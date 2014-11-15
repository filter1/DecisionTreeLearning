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

	public String getAttributeValue( String attribute ) {
		if (attribute.equals("buying")) {
			return this.buying;
		} else if (attribute.equals("maint")) {
			return this.maint;
		} else if (attribute.equals("doors")) {
			return this.doors;
		} else if (attribute.equals("persons")) {
			return this.persons;
		} else if (attribute.equals("lug_boot")) {
			return this.lug_boot;
		} else if (attribute.equals("safety")) {
			return this.safety;
		} else if (attribute.equals("targetClass")) {
			return this.targetClass;
		}
		return null;
	}

}