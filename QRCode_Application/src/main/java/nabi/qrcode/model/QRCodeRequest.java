package nabi.qrcode.model;

public class QRCodeRequest {

	 private String id;
	    private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return "QRCodeRequest [id=" + id + ", name=" + name + ", getId()=" + getId() + ", getName()=" + getName()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
					+ "]";
		}
	    
	    
}
