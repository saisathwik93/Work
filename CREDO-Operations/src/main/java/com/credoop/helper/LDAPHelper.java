package com.credoop.helper;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import com.credoop.bean.LdapRequest;
import com.credoop.bean.LdapResponse;
import com.credoop.exceptions.AccessDeniedException;
import com.credoop.exceptions.ResourceNotFoundException;

@Service
public class LDAPHelper {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Value("${ldap.basedn}")
	private String ldapBaseDn;

	public LdapResponse findUser(LdapRequest request) {
		LdapResponse response = new LdapResponse();
		if (validRequest(request)) {
			try {
				MultiValueMap<String, Object> map = findUser(request.getUsername(),request.getPassword());
				BeanUtils.copyProperties(request, response);
				if (map == null) {
					response.setError("Username/password does not exist in Active Directory.");
				}
				response.setProperties(map);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ResourceNotFoundException(
						"It seems user doesnot exist in our list or something went wrong with the request!");
			}
		}
		return response;
	}

	private boolean validRequest(LdapRequest request) {
		if (request == null) {
			throw new NullPointerException("Request object is empty");
		}
		if (StringUtils.isEmpty(request.getAppKey())) {
			throw new NullPointerException("Application key cannot be empty");
		}
		if (StringUtils.isEmpty(request.getUsername())) {
			throw new NullPointerException("Username cannot be empty");
		}
		if (StringUtils.isEmpty(request.getPassword())) {
			throw new NullPointerException(" Password cannot be empty");
		}
		if (!AccessMapper.checkPermission(request.getAppKey())) {
			throw new AccessDeniedException("Sorry you do not have permission to acess this URL");
		}
		return true;
	}

	private MultiValueMap<String, Object> findUser(String username, String password) {
		MultiValueMap<String, Object> map = null;

		map = findUserInMocrNt1(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in MOCR-NT1", username));
			return map;
		}
	
		map = findUserInOpc(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in hq.opc", username));
			return map;
		}

		map = findUserInAstexUS(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in Supergen.com - AstexUS", username));
			return map;
		}

		map = findUserInToi(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in taihopui.com - TOI", username));
			return map;
		}

		map = findUserInEu(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in eu.otsuka.com - EU", username));
			return map;
		}

		map = findUserInEuIppnet(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in ippnet.eu.otsuka.com", username));
			return map;
		}

		map = findUserInEuOfri(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in ofri.eu.otsuka.com", username));
			return map;
		}

		map = findUserInAvanir(username,password);
		if (map != null) {
			System.out.println(String.format("USER [%s] found in Avanir", username));
			return map;
		}

		System.err.println(String.format("USER [%s] not found in any of the domain", username));
		return map;
	}

	private MultiValueMap<String, Object> findUserInMocrNt1(String username,String password) {
		return find("dc=MOCR-NT1,dc=OTSUKA,dc=com", username, password);
	}
	
	

	private MultiValueMap<String, Object> findUserInOpc(String username, String password) {
		return find("dc=hq,dc=opc", username, password);
	}

	private MultiValueMap<String, Object> findUserInAstexUS(String username, String password) {
		return find("dc=Supergen,dc=com", username, password);
	}

	private MultiValueMap<String, Object> findUserInToi(String username, String password) {
		return find("dc=taihopui,dc=com", username, password);
	}

	private MultiValueMap<String, Object> findUserInEu(String username, String password) {
		return find("dc=eu,dc=OTSUKA,dc=com", username, password);
	}

	private MultiValueMap<String, Object> findUserInEuIppnet(String username, String password) {
		return find("dc=ippnet,dc=eu,dc=OTSUKA,dc=com", username, password);
	}

	private MultiValueMap<String, Object> findUserInEuOfri(String username, String password) {
		return find("dc=ofri,dc=eu,dc=OTSUKA,dc=com"
				, username, password);
	}

	private MultiValueMap<String, Object> findUserInAvanir(String username, String password) {
		return find("dc=avn,dc=com", username, password);
	}

	private MultiValueMap<String, Object> find(final String baseDn, String username, String password) {
		MultiValueMap<String, Object> map = null;
		AndFilter filter = new AndFilter();

		if (username.contains("@")) {
			filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("mail", username));
		} else {
			filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("sAMAccountName", username));
		}
		try {
			boolean flag=ldapTemplate.authenticate(baseDn, filter.toString(), password); 
			//Validating the password from the Active Directory and authenticating
			if(flag) {
				map = ldapTemplate.searchForObject(baseDn, filter.toString(), new PropertyMapper());
			}else {
				return null;
			}
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	private static class PropertyMapper implements ContextMapper<MultiValueMap<String, Object>> {

		@Override
		public MultiValueMap<String, Object> mapFromContext(Object obj) throws NamingException {
			DirContextAdapter dca = (DirContextAdapter) obj;
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			Attributes attrs = dca.getAttributes();
			if (attrs != null && attrs.size() > 0) {
				NamingEnumeration<? extends Attribute> iterator = attrs.getAll();
				while (iterator.hasMore()) {
					Attribute attr = iterator.next();
					String attrId = attr.getID();
					NamingEnumeration<?> subIterator = attr.getAll();
					while (subIterator.hasMore()) {
						Object o = subIterator.next();
						map.add(attrId, o);
					}
}
}
  return map;
}
}
}