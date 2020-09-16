package workshop.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class DepartmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "dept_no")
	private Integer deptNo;

	@Column(name = "dept_name")
	@Basic(fetch = FetchType.LAZY, optional = false)
	private String deptName;

	public DepartmentEntity() {
	}

	public DepartmentEntity(Integer deptNo, String deptName) {
		this.deptNo = deptNo;
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptNo=" + deptNo + ", deptName=" + deptName + "]";
	}
}
