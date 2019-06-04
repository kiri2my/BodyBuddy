package com.bodybuddy.hey;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KwonController {

	private static final Logger logger = LoggerFactory.getLogger(KwonController.class);

	ModelAndView mav;

	/*
	 * @Autowired private GoogleConnectionFactory googleConnectionFactory;
	 * 
	 * @Autowired private OAuth2Parameters googleOAuth2Parameters;
	 */
	@RequestMapping(value = "/aaaaaa", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "main";
	}

	/*
	 * @RequestMapping(value = "/googleLogin", method = RequestMethod.POST) public
	 * String doGoogleSignInActionPage(HttpServletResponse response, Model model)
	 * throws Exception { OAuth2Operations oauthOperations =
	 * googleConnectionFactory.getOAuthOperations(); String url =
	 * oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE,
	 * googleOAuth2Parameters); System.out.println("/member/googleSignIn, url : " +
	 * url); model.addAttribute("url", url); return "login/googleLogin";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/googleSignInCallback", method = RequestMethod.GET)
	 * public String doSessionAssignActionPage(HttpServletRequest request) throws
	 * Exception { System.out.println("/member/googleSignInCallback"); String code =
	 * request.getParameter("code");
	 * 
	 * OAuth2Operations oauthOperations =
	 * googleConnectionFactory.getOAuthOperations(); AccessGrant accessGrant =
	 * oauthOperations.exchangeForAccess(code,
	 * googleOAuth2Parameters.getRedirectUri(), null);
	 * 
	 * String accessToken = accessGrant.getAccessToken(); Long expireTime =
	 * accessGrant.getExpireTime(); if (expireTime != null && expireTime <
	 * System.currentTimeMillis()) { accessToken = accessGrant.getRefreshToken();
	 * System.out.printf("accessToken is expired. refresh token = {}", accessToken);
	 * } Connection<Google> connection =
	 * googleConnectionFactory.createConnection(accessGrant); Google google =
	 * connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
	 * 
	 * PlusOperations plusOperations = google.plusOperations(); Person profile =
	 * plusOperations.getGoogleProfile(); UserVO vo = new UserVO();
	 * System.out.println(profile.getDisplayName()); vo.setUser_email("구글 로그인 계정");
	 * vo.setUser_name(profile.getDisplayName()); vo.setUser_snsId("g" +
	 * profile.getId()); HttpSession session = request.getSession(); // vo =
	 * service.googleLogin(vo);
	 * 
	 * session.setAttribute("login", vo);
	 * 
	 * return "redirect:/"; }
	 */

}
