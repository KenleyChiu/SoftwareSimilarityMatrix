package Backend;

import java.io.*;

public class PythonScript {
    public static void main(String a[]) {
        try {
//
//                String prg = "import os\n" +
//                        "from git.repo.base import Repo\n" +
//                        "from datetime import datetime\n" +
//                        "\n" +
//                        "\n" +
//                        "# create path name\n" +
//                        "path = \"Codes\" \n" +
//                        "\n" +
//                        "# create dataset dir\n" +
//                        "if not os.path.exists(path):\n" +
//                        "    os.makedirs(path)\n" +
//                        "\n" +
//                        "# go to dir\n" +
//                        "os.chdir(path)\n" +
//                        "\n" +
//                        "# acquire repositories\n" +
//                        "with open(\"D:\\\\Downloads\\\\College stuff\\\\SoftwareSimilarityMatrix\\\\repo.txt\",'r') as repo_list:\n" +
//                        "    for count, repo in enumerate(repo_list):    \n" +
//                        "       pair_id = \"LBYCPD2_Pair\" + str(count)\n" +
//                        "       print(\"Downloading \", pair_id)\n" +
//                        "       Repo.clone_from(repo.strip(), pair_id, branch='master')\n" +
//                        "   \n" +
//                        "print(\"---- COMPLETED ----\")";
//                BufferedWriter out = new BufferedWriter(new FileWriter("RepoGrabber.py"));
//                out.write(prg);
//                out.close();
            Process p = Runtime.getRuntime().exec("python RepoGrabber.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            System.out.println("Completed:" + in.readLine());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

