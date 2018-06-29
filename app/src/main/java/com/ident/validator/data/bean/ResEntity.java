package com.ident.validator.data.bean;

public class ResEntity {

	public ResEntity(int bgRes, int logoRes, int successRes, int failureRes, int sealRes, int productRes,
			int waveColorRes,String url) {
		super();
		this.bgRes = bgRes;
		this.logoRes = logoRes;
		this.successRes = successRes;
		this.failureRes = failureRes;
		this.sealRes = sealRes;
		this.productRes = productRes;
		this.waveColorRes = waveColorRes;
		this.url = url;
	}

	public int bgRes;
	public int logoRes;
	public int successRes;
	public int failureRes;
	public int sealRes;
	public int waveColorRes;
	public int productRes;
	public String url;

}
