package com.gabriel.gcscollegeAPI.services;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.gcscollegeAPI.model.Role;
import com.gabriel.gcscollegeAPI.repositories.RoleRepository;


@Service
public class RoleServiceImpl {

	@Autowired
	private RoleRepository roleRepository;


	public Collection<Role> findAll() {
		return roleRepository.findAll();
	}

	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}
	

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	public Role saveOrUpdate(Role role) {
		return roleRepository.saveAndFlush(role);
	}

	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			roleRepository.deleteById(id);
			jsonObject.put("message", "Role deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}