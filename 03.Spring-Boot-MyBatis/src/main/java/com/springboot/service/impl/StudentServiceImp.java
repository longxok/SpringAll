package com.springboot.service.impl;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bean.Student;
import com.springboot.mapper.StudentMapper;
import com.springboot.service.StudentService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("studentService")
public class StudentServiceImp implements StudentService{

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public int add(Student student) {
		return this.studentMapper.add(student);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public int update(Student student) {
		return this.studentMapper.update(student);
	}

	@Override
	public int deleteBysno(String sno) {
		return this.studentMapper.deleteBysno(sno);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Student queryStudentBySno(String sno) {
		Student student = this.studentMapper.queryStudentBySno(sno);
		student.setName("yh");

		((StudentService)AopContext.currentProxy()).update(student);
		//update(student);
		add(new Student("123"));


		return student;
	}
}
