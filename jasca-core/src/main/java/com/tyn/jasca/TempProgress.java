package com.tyn.jasca;

import edu.umd.cs.findbugs.FindBugsProgress;

public class TempProgress implements FindBugsProgress {

	private static void w(String s) {
		System.out.println(s);
	}
	
	// 1
	// project �� add �� DIR ����
	@Override
	public void reportNumberOfArchives(int numArchives) {
		w("reportNumberOfArchives, " + numArchives);
	}
	
	// 2
	// project �� add �Ȱ� archive ��
	// WorkListItem(filesystem:D:\securecoding\workspace\sampleweb, true, SPECIFIED)
	@Override
	public void startArchive(String name) {
		w("startArchive, " + name);
	}
	
	// 3
	@Override
	public void finishArchive() {
		w("finishArchive");
	}
	
	// 4
	// 
	@Override
	public void predictPassCount(int[] classesPerPass) {
		w("predictPassCount, " + classesPerPass[0] + ", " + classesPerPass[1]);
	}
	
	// 5 �м�����
	// 5 6 7 �ݺ� 4�� ���ڷ� �Ѿ�� �迭 �����ŭ
	@Override
	public void startAnalysis(int numClasses) {
		w("startAnalysis, " + numClasses);
	}
	
	// 7 �м�
	@Override
	public void finishClass() {
		w("finishClass");
	}
	
	//  6 �м�
	@Override
	public void finishPerClassAnalysis() {
		w("finishPerClassAnalysis");
	}
}
