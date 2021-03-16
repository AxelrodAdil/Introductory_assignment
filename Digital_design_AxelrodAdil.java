import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Digital_design_AxelrodAdil {
    /**
     *
     * Created by Adil Myktybek
     * on 16.03.2021, 22:57
     *
     */

    /*
    Вход: 3[xyz]4[xy]z
Выход: xyzxyzxyzxyxyxyxyz

2[3[x]y]  = xxxyxxxy

Дополнительное задание:
Проверить входную строку на валидность:
        Pattern pattern_ = Pattern.compile(".*\\d[A-Z].*");
    */
    static int count_check=0;
    static StringBuilder sb=new StringBuilder();
    static int left_index=0;
    static int right_index=0;
    static String temp_str="";

    static boolean check_it(String[] arr){
        int count_right=0;
        int count_left=0;
        for (String s : arr) {
            if (s.equals("[")) {
                count_right++;
            }
            if (s.equals("]")) {
                count_left++;
                count_check=count_left;
            }
        }
        return count_left == count_right;
    }

    static int[] arr_int(String[] arr_str){
        int arr_int_length=count_check;
        int[] arr_int=new int[arr_int_length];
        for (int i = 0; i< arr_str.length; i++){
            if(arr_str[i].equals("[")){
                if(i==0){
                    arr_int[arr_int_length-1]=1;
                } else {
                    arr_int[arr_int_length-1]=Integer.parseInt(arr_str[i-1]);
                }
                arr_int_length--;
            }
        }
        return arr_int;
    }

    static void required(int j, String temp_){
        String[] arr_str=temp_.split("");
        for (int z=j+1;;z++) {
            if(arr_str[z].equals("]")){
                break;
            }
            right_index++;
            sb.append(arr_str[z]);
        }
    }

    static void replace_String(int left_index, int right_index, String str2){
        temp_str = temp_str.substring(0,left_index)+str2+temp_str.substring(right_index);
    }

    static boolean check_the_string_for_validity(){
        System.out.println(temp_str.matches(".*\\d[A-Z].*")); // "[\\d[^a-zA-z]]+"
        return Pattern.matches(".*\\d[A-Z].*", temp_str);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        temp_str=str;
        String[] arr_str =str.split("");  // charAt()...

        //if(check_it(arr_str) & check_the_string_for_validity()){
        if(check_it(arr_str)){
            int[] arr_int=arr_int(arr_str);
            String[] arr_str_ans=new String[arr_int.length];
            int arr_int_length=count_check;
            int k=0;

            for(int i=0;i<count_check;i++) {    // brute
                int left_branches=0;
                for (int j=0;j<arr_str.length;j++) {
                    if (arr_str[j].equals("[")) {
                        left_branches++;
                    }
                    if (left_branches == arr_int_length) {
                        for(int o=0;o<arr_int[k];o++) {
                            right_index=j;
                            left_index=j;
                            required(j, temp_str);
                        }
                        arr_str_ans[k]=sb.toString();
                        replace_String(left_index-1, right_index+2, arr_str_ans[k]);
                        sb.setLength(0);
                        k++;
                        arr_int_length--;
                    }
                }
            }
            System.out.print("\nAnswer: "+temp_str);
        } else {
            System.out.println("False: Error!");
        }
    }
}