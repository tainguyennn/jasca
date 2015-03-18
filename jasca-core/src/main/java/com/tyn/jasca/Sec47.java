package com.tyn.jasca;

/**
 * 
 * @author S.J.H.
 */
public class Sec47 {
	
	public static Violation conv(Violation v) {
		if ("F".equals(v.getEngine())){
			return convFindBugs(v);
		}
		else {
			return convPMD(v);
		}
	}
	
	private static Violation convFindBugs(Violation v) {
		String type = v.getType();
		if ("SQL_NONCONSTANT_STRING_PASSED_TO_EXECUTE".equals(type)) {
			v.setType("01.SQL����");
		}
		else if ("PATH_TRAVERSAL_IN".equals(type)) {
			v.setType("02.������۹��ڿ�����");
		}
		else if ("XSS_REQUEST_PARAMETER_TO_SERVLET_WRITER".equals(type)) {
			v.setType("03.ũ�ν�����Ʈ��ũ��Ʈ");
		}
		else if ("COMMAND_INJECTION".equals(type)) {
			v.setType("04.�ü����ɾ����");
		}
		else if ("UNVALIDATED_REDIRECT".equals(type)) {
			v.setType("06.�ŷڵ����ʴ�URL�ּҷ��ڵ����ӿ���");
		}
		else if ("XQUERY_INJECTION".equals(type)) {
			v.setType("07.XQuery����");
		}
		else if ("XPATH_INJECTION".equals(type)) {
			v.setType("08.XPath����");
		}
		else if ("LDAP_INJECTION".equals(type)) {
			v.setType("09.LDAP����");
		}
		return v;
	}
	
	private static Violation convPMD(Violation v) {
		String type = v.getType();
		if ("DynamicClassLoading".equals(type)) {
			v.setType("30.���Ἲ�˻�����ڵ�ٿ�ε�");
		}
		else if ("ReverseDNSResolution".equals(type)) {
			v.setType("46.DNSlookup�������Ѻ��Ȱ���");
		}
		else if ("UseDangerousMethod".equals(type)) {
			v.setType("47.�����API���");
		}
		return v;
	}
}
