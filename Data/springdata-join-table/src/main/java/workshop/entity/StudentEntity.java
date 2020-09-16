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
@Table(name = "students")
public class StudentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@Basic(fetch = FetchType.LAZY, optional = false)
	private String name;

	@Column(name = "dept_no")
	@Basic(fetch = FetchType.LAZY, optional = false)
	private Integer deptNo;

	@Column
	@Basic(fetch = FetchType.LAZY, optional = false)
	private Integer age;

	public StudentEntity() {
	}

	public StudentEntity(String name, Integer deptNo, Integer age) {
		this.name = name;
		this.deptNo = deptNo;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", deptNo=" + deptNo + ", age=" + age + "]";
	}
}
