package fr.fms.job;

import java.sql.ResultSet;

public class IJobImpl implements IJob{

	@Override
	public boolean VerifieUser(String user) {
		try {
			String StrSql = "SELECT Login FROM T_Users where Login ='" + user + "'";
			ResultSet resultSet = connexion.executeQuery(StrSql);
			if(resultSet.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean VerifiePsw(String user, String psw) {
		if(VerifieUser(user)) {
			try {
				String StrSql = "SELECT Login FROM T_Users where password ='" + psw + "'";
				ResultSet resultSet = connexion.executeQuery(StrSql);
				if(resultSet.next())
					if(resultSet.getString(1).equals(user))
						return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
