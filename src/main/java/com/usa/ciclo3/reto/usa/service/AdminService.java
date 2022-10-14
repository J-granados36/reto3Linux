package com.usa.ciclo3.reto.usa.service;


import com.usa.ciclo3.reto.usa.model.Admin;
import com.usa.ciclo3.reto.usa.model.Message;
import com.usa.ciclo3.reto.usa.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }

    public Optional<Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }

    public Admin save(Admin admin){
        if (admin.getIdAdmin()==null){
            return adminRepository.save(admin);
        }else{
            Optional<Admin> Adaux=adminRepository.getAdmin(admin.getIdAdmin());
            if (Adaux.isEmpty()){
                return adminRepository.save(admin);
            }else{
                return admin;
            }
        }
    }

    public Admin update(Admin admin){
        if (admin.getIdAdmin() != null){
            Optional<Admin> Ad= adminRepository.getAdmin(admin.getIdAdmin());
            if (Ad.isEmpty()){
                if (admin.getEmail() != null){
                    Ad.get().setEmail(admin.getEmail());
                }
                if (admin.getPassword() != null){
                    Ad.get().setPassword(admin.getPassword());
                }
                if (admin.getName() != null){
                    Ad.get().setName(admin.getName());
                }
                adminRepository.save(Ad.get());
                return Ad.get();
            }else{
                return admin;
            }
        }else {
            return admin;
        }
    }


    public boolean deleteAdmin(int id){
        Boolean dAd = getAdmin(id).map(admin -> {
            adminRepository.delete(admin);
            return true;
        }).orElse(false);
        return dAd;
    }

}
